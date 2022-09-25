package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage { ;
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE_NAME = "duke.txt";

    private static File dataFile;

    public Storage() {
        dataFile = new File(DATA_DIRECTORY, DATA_FILE_NAME);
    }

    public static void createFile() {
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
            System.out.println(Message.CREATE_FILE_FAIL_ERROR_MESSAGE);
        }
    }

    private ArrayList<String> readFile() throws FileNotFoundException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }

        if (dataFile.length() == 0) {
            return null;
        }

        ArrayList<String> taskData = null;

        try {
            taskData = (ArrayList<String>) Files.readAllLines(dataFile.toPath());
        } catch (IOException e) {
            // Fail to read file
        }

        return taskData;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            ArrayList<String> taskData = readFile();
            if (taskData != null) {
                taskList = Parser.parseTaskData(taskData);
            }
        } catch (FileNotFoundException e) {
            createFile();
        }

        return taskList;
    }

    public void saveTasks(ArrayList<Task> tasks) {
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
            System.out.println(Message.SAVE_TASK_FAIL_ERROR_MESSAGE);
        }
    }
}
