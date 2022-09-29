package Storage;

import Tasks.Task;
import Tasks.TaskList;
import Tasks.TaskType;
import Exception.DataCorruptedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH =  "./data/data.md";

    public int initialiseTaskFromFile(TaskList taskList)
            throws FileNotFoundException, DataCorruptedException {

        File f = new File(FILE_PATH);
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
                    throw new DataCorruptedException();
            }
        }
        return taskList.getTaskListSize();

    }

    public void createNewFile() throws IOException {
        addMarkdownHeader();
    }

    // Take in Task List and Save it to file
    public void updateWholeFile(TaskList taskList) throws IOException {
        addMarkdownHeader();
        for (Task task : taskList.getInputLists()) {
            appendTaskToFile(task);
        }
    }

    public void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    public void addMarkdownHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("|Task Type | Done | Description | Time |\n"
                + "|----------|------|-------------|------|\n");
        fw.close();
    }

    private String toMarkdown(Task task) {
        String type = task.getClass().getSimpleName();
        boolean bool = task.hasCompleted();
        String description = task.getTaskName();
        String time = "";
        if (!type.equals("Todo")) {
            time = task.getTime();
        }
        return "| " + type + " | " + String.valueOf(bool) + " | " + description + " | " + time + " |\n";
    }
}
