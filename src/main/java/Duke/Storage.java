package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.TasksList;
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

    public static void loadTasksToTasksList(TasksList tasksList) {
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
                    tasksList.addToTasksList(todoTask);
                    String isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        tasksList.setTaskDoneStatus(taskNumber, true);
                    }
                    break;
                case 'D':
                    Deadline deadlineTask = new Deadline(fileWords[2], 'D', fileWords[ 3 ]);
                    tasksList.addToTasksList(deadlineTask);
                    isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        tasksList.setTaskDoneStatus(taskNumber, true);
                    }
                    break;
                case 'E':
                    Event eventTask = new Event(fileWords[ 2 ], 'E', fileWords[ 3 ]);
                    tasksList.addToTasksList(eventTask);
                    isMarked = fileWords[1].replaceAll("\\s+","");
                    if (isMarked.equals("1")){
                        tasksList.setTaskDoneStatus(taskNumber, true);
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

    public static void loadTasktoDataFile(TasksList tasksList) throws IOException {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            int taskNumber = tasksList.getTaskNumberOfInterest();
            fw.write(tasksList.printTaskToDataFile(taskNumber));
            fw.close();
        } catch (IOException ioException) {
            System.out.printf("Error has occured when loading the task to data file.");
            ioException.printStackTrace();
        }
    }

    public static void updateTaskInDataFile(TasksList tasksList, String commandType) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE_PATH));
            int taskNumber = tasksList.getTaskNumberOfInterest();
            String updatedTaskToLoadInDataFile = "";
            if (commandType.equals("edit")) {
                updatedTaskToLoadInDataFile = tasksList.printTaskToDataFile(taskNumber).replace("\n", "");
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
