package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Represent the storage that stores all the tasks into the data file.
 */
public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE_NAME = "duke.txt";

    private static File dataFile;

    public Storage() {
        dataFile = new File(DATA_DIRECTORY, DATA_FILE_NAME);
    }

    /**
     * Create the file <code>data/duke.txt</code>
     * If the data file exists, nothing will be done.
     * If the directory <code>data</code> does not exist, it will be created before creating the new file.
     *
     * @param ui User interface of the application.
     */
    public static void createFile(Ui ui) {
        try {
            if (dataFile.exists()) {
                return;
            }

            // Create parent directory if it does not exist
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }

            dataFile.createNewFile();
        } catch (IOException e) {
            ui.showCreateFileFailErrorMessage();
        }
    }

    /**
     * Return all the lines in the text file as a list of strings.
     * If the data file is blank or the file cannot be read, an empty list is returned.
     * @return A list of strings where each string is a line in the text file.
     * @throws IOException If the file is not found, or if the file cannot be read.
     */
    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }

        // If the data file is blank, return a blank list.
        if (dataFile.length() == 0) {
            return new ArrayList<>();
        }

        ArrayList<String> taskData;

        try {
            taskData = (ArrayList<String>) Files.readAllLines(dataFile.toPath());
        } catch (IOException e) {
            // Fail to read file
            throw new IOException();
        }

        return taskData;
    }

    /**
     * Return a list of <code>Task</code> objects specified in the <code>duke.txt</code> file.
     * @param ui User interface of the application
     * @return A list of <code>Task</code> objects specified in the <code>duke.txt</code> file.
     */
    public ArrayList<Task> loadTasks(Ui ui) {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            ArrayList<String> taskData = readFile();
            if (taskData != null) {
                taskList = Parser.parseTaskData(taskData);
            }
        } catch (IOException e) {
            createFile(ui);
        }

        return taskList;
    }

    /**
     * Save the list of tasks into the data file.
     * @param tasks List of <code>Task</code> objects.
     * @param ui User interface of the application
     */
    public void saveTasks(ArrayList<Task> tasks, Ui ui) {
        try {
            // Create parent directory if it does not exist
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }

            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(dataFile);
            for (Task task : tasks) {
                fileWriter.write(task.getStringForSave() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.showSaveTaskFailErrorMessage();
        }
    }
}
