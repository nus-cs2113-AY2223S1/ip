package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_PATH = "data/data.txt";
    public static void createDataFile() {
        try {
            File file = new File(DATA_FILE_PATH);
            if (!file.getParentFile().mkdirs()) {
                System.out.println("Error creating parent folder(s)");
            }
            if (file.createNewFile()) {
                System.out.printf("File created at %s\n", DATA_FILE_PATH);
            } else {
                System.out.printf("File already exists at %s\n", DATA_FILE_PATH);
            }
        } catch (IOException ioException) {
            System.out.printf("Error creating file: Could not create file at %s\n", DATA_FILE_PATH);
            ioException.printStackTrace();
        }
    }

    public static void loadTasksToTasksList(TaskList taskList) {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner s = new Scanner(dataFile);
            int taskNumber = -1;
            while (s.hasNext()) {
                String[] fileWords = s.nextLine().split("\\| ");
                taskNumber += 1;
                switch (fileWords[0].charAt(0)) {
                case 'T':
                    Todo todoTask = new Todo(fileWords[2], 'T');
                    taskList.addToTasksList(todoTask);
                    String isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        taskList.setTaskDoneStatus(taskNumber, true);
                    }
                    break;
                case 'D':
                    Deadline deadlineTask = new Deadline(fileWords[2], 'D', fileWords[ 3 ]);
                    taskList.addToTasksList(deadlineTask);
                    isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        taskList.setTaskDoneStatus(taskNumber, true);
                    }
                    break;
                case 'E':
                    Event eventTask = new Event(fileWords[ 2 ], 'E', fileWords[ 3 ]);
                    taskList.addToTasksList(eventTask);
                    isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        taskList.setTaskDoneStatus(taskNumber, true);
                    }
                    break;
                default:
                    System.out.println("Error reading data from file: Invalid format");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found! Give me a moment to create it!");
            createDataFile();
        }
    }

    public static void loadTasktoDataFile(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            int taskNumber = taskList.getTaskNumberOfInterest();
            fw.write(taskList.loadTaskToDataFile(taskNumber));
            fw.close();
        } catch (IOException ioException) {
            System.out.printf("Error has occurred when loading the task to data file.");
            ioException.printStackTrace();
        }
    }

    public static void updateTaskInDataFile(TaskList taskList, String commandType) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE_PATH));
            int taskNumber = taskList.getTaskNumberOfInterest();
            String updatedTaskToLoadInDataFile = "";
            if (commandType.equals("edit")) {
                updatedTaskToLoadInDataFile = taskList.loadTaskToDataFile(taskNumber).replace("\n", "");
            }
            lines.set(taskNumber, updatedTaskToLoadInDataFile);
            lines.removeIf(String::isEmpty);
            Files.write(Paths.get(DATA_FILE_PATH), lines);
        } catch (IOException ioException) {
            System.out.printf("Error occured when updating the task in data file.");
            ioException.printStackTrace();
        }
    }
}
