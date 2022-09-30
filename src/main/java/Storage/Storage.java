package Storage;

import Tasks.Task;
import Tasks.TaskList;
import Tasks.TaskType;
import Exception.DataCorruptedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * Represents the class which handles file handling and saving.
 * It supports write and read operation.
 */
public class Storage {
    private static final String DIRECTORY_PATH = Paths.get(".", "data").toString();
    private static final String FILE_NAME = "data.md";
    private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();
    private static final File f = new File(FILE_PATH);

    /**
     * Returns the number of task read from the local file.
     * If the file do not exist/corrupted, it will create one with a md table header.
     *
     * @param taskList The task list object to store the tasks
     * @return Number of tasks read in from the file.
     * @throws DataCorruptedException If the data is corrupted.
     * @throws IOException If the file do not exist.
     */
    public int initialiseTaskFromFile(TaskList taskList)
            throws DataCorruptedException, IOException {

        if (!f.exists()) {
            // File not found, make new directory and new file with the md header.
            File directory = new File(DIRECTORY_PATH);
            directory.mkdir();
            createNewFile();
            throw new IOException();
        }
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] input = s.nextLine().split("\\|");

            // Detect the markdown header and skip reading it.
            if (input[1].strip().equals("Task Type") || input[1].strip().equals("----------")) {
                continue;
            }

            String type = input[1].strip();
            boolean isCompleted = Boolean.parseBoolean(input[2].strip());
            String description = input[3].strip();
            String time = input[4].strip();
            switch (type) {
                case "Event":
                    taskList.addTaskToList(description + "/at " + time, TaskType.EVENT, isCompleted);
                    break;
                case "Deadline":
                    taskList.addTaskToList(description + " /by " + time, TaskType.DEADLINE, isCompleted);
                    break;
                case "Todo":
                    taskList.addTaskToList(description, TaskType.TODO, isCompleted);
                    break;
                default:
                    // Corrupted Data. Delete the file and create a new file
                    s.close();
                    f.delete();
                    createNewFile();
                    throw new DataCorruptedException();
            }
        }
        s.close();
        return taskList.getTaskListSize();
    }

    /**
     * Facilitates creation of a new file and add Markdown table header.
     *
     * @throws IOException if the file creation is erroneous.
     */
    public void createNewFile() throws IOException {
        f.createNewFile();
        addMarkdownHeader();
    }

    /**
     * Facilitates saving and writing of file due to changes in the task list.
     * If the file is missing, it will create a new one.
     *
     * @param taskList The list to save in the file.
     * @throws IOException If file handling is erroneous.
     */
    public void updateWholeFile(TaskList taskList) throws IOException {
        if (!f.exists()) {
            File directory = new File(DIRECTORY_PATH);
            directory.mkdir();
            createNewFile();
        }
        addMarkdownHeader();
        for (Task task : taskList.getInputLists()) {
            appendTaskToFile(task);
        }
    }

    /**
     * Append a single task to the file.
     *
     * @param task The task to save.
     * @throws IOException if the file handling is erroneous.
     */
    public void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    /**
     * Adds a markdown table header to the file.
     *
     * @throws IOException if file handling is erroneous.
     */
    public void addMarkdownHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("|Task Type | Done | Description | Time |\n"
                + "|----------|------|-------------|------|\n");
        fw.close();
    }

    /**
     * Parse task object to a markdown table format for better visualisation.
     *
     * @param task Task to save.
     * @return String format of the markdown row.
     */
    private String toMarkdown(Task task) {
        String type = task.getClass().getSimpleName();
        boolean bool = task.hasCompleted();
        String description = task.getTaskName();
        String time = "";
        if (!type.equals("Todo")) {
            time = task.getTime();
        }
        return "| " + type + " | " + String.valueOf(bool)
                + " | " + description + " | " + time + " |\n";
    }
}
