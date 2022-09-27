package Duke;

import Duke.Tasks.*;
import Duke.Exceptions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Duke {
    private static TasksList tasksList = new TasksList();
    private static final String DATA_FILE_PATH = "data/data.txt";

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitText() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void createDataFile() {
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

    private static void loadTasksToTasksList() {
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

    public static void loadTasktoDataFile(int taskNumber) throws IOException {
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
            fw.write(tasksList.printTaskToDataFile(taskNumber));
            fw.close();
        } catch (IOException ioException) {
            System.out.printf("Error has occured when loading the task to data file.");
            ioException.printStackTrace();
        }
    }

    public static void updateTaskInDataFile(int taskNumber, String commandType) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE_PATH));
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

    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        loadTasksToTasksList();
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "bye":
                printExitText();
                System.exit(0);
            case "list":
                tasksList.printTaskList();
                break;
            case "todo":
                tasksList.addTodoTask(inputWords);
                loadTasktoDataFile(tasksList.getTaskNumberOfInterest());
                break;

            case "deadline":
                tasksList.addDeadlineTask(inputWords);
                loadTasktoDataFile(tasksList.getTaskNumberOfInterest());
                break;

            case "event":
                tasksList.addEventTask(inputWords);
                loadTasktoDataFile(tasksList.getTaskNumberOfInterest());
                break;

            case "mark":
                tasksList.doMarkTask(inputWords);
                updateTaskInDataFile(tasksList.getTaskNumberOfInterest(), "edit");
                break;

            case "unmark":
                tasksList.doUnmarkTask(inputWords);
                updateTaskInDataFile(tasksList.getTaskNumberOfInterest(), "edit");
                break;

            case "delete":
                tasksList.doDeleteTask(inputWords);
                updateTaskInDataFile(tasksList.getTaskNumberOfInterest(), "delete");
                break;

            default:
                System.out.println("Please provide a correct command!");
            }
        }
    }
}
