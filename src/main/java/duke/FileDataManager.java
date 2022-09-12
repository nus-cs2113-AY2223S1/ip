package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileDataManager {

    public static final String TEXT_FILE_NAME = "/duke.txt";
    File dataFile;

    public FileDataManager() {
        String filePath = retrieveFilePath();
        dataFile = new File(filePath);
    }

    public static String retrieveFilePath() {
        Path path = Paths.get("src", "main", "java", "duke", "data");
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            new File(path.toString()).mkdirs();
        }
        return path + TEXT_FILE_NAME;
    }

    public void loadData(TaskManager taskManager) {
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("Loading existing data...");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] inputs;
        String taskType, description, status, time = "";
        while (fileScanner.hasNext()) {
            inputs = acceptAndProcessInput(fileScanner);
            taskType = inputs[0];
            status = inputs[1];
            description = inputs[2];
            if (inputs.length > 3) {
                time = inputs[3];
            }
            Task task = TaskManager.initialiseTaskFromParameters(taskType, description, status, time);
            taskManager.loadTask(task);
        }
    }

    private static String[] acceptAndProcessInput(Scanner fileScanner) {
        String input = fileScanner.nextLine().trim();
        return input.split(" \\| ", 4);
    }

    public void saveData(TaskManager taskManager) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (int taskNumber = 0; taskNumber <= taskManager.getNumTasks(); taskNumber++) {
                Task task = taskManager.getTask(taskNumber);
                logTaskIntoDataFile(fileWriter, task);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void logTaskIntoDataFile(FileWriter fileWriter, Task task) throws IOException {
        fileWriter.write(task.getTaskType());
        fileWriter.write(" | ");
        fileWriter.write(task.getStatusIcon());
        fileWriter.write(" | ");
        fileWriter.write(task.getDescription());
        if (task instanceof Event) {
            fileWriter.write(" | ");
            fileWriter.write(((Event) task).getTime());
        } else if (task instanceof Deadline) {
            fileWriter.write(" | ");
            fileWriter.write(((Deadline) task).getDeadline());
        }
        fileWriter.write(System.lineSeparator());
    }

}
