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

    private static void loadTasksToTasksList() throws InvalidCommandFormatException, FileNotFoundException{
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String[] fileWords = s.nextLine().split("\\| ");
                switch (fileWords[0].charAt(0)) {
                case 'T':
                    Todo todoTask = new Todo(fileWords[2], 'T');
                    tasksList.addToTasksList(todoTask);
                    break;
                case 'D':
                    Deadline deadlineTask = new Deadline(fileWords[2], 'D', fileWords[ 3 ]);
                    tasksList.addToTasksList(deadlineTask);
                    break;
                case 'E':
                    Event eventTask = new Event(fileWords[ 2 ], 'E', fileWords[ 3 ]);
                    tasksList.addToTasksList(eventTask);
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
        FileWriter fw = new FileWriter(DATA_FILE_PATH, true);
        fw.write(tasksList.printTaskToDataFile(taskNumber));
        fw.close();
    }

    public static void updateTaskDoneInDataFile(int taskNumber) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(DATA_FILE_PATH));
        String updatedTaskToLoadInDataFile = tasksList.printTaskToDataFile(taskNumber).replace("\n", "");
        lines.set(taskNumber, updatedTaskToLoadInDataFile);
        Files.write(Paths.get(DATA_FILE_PATH), lines);
    }

    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException {
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
                break;
            case "list":
                tasksList.printTaskList();
                break;
            case "mark":
                if (inputWords.length == 1) {
                    String correctFormatMessage = "The command should be 'mark (task number to mark)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                int taskNumber =  Integer.parseInt(inputWords[1]) - 1;
                tasksList.markTask(taskNumber, "mark", true);
                updateTaskDoneInDataFile(taskNumber);
                break;
            case "unmark":
                if (inputWords.length == 1) {
                    String correctFormatMessage = "The command should be 'unmark (task number to mark)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                taskNumber = Integer.parseInt(inputWords[ 1 ]) - 1;
                tasksList.markTask(taskNumber, "unmark", false);
                updateTaskDoneInDataFile(taskNumber);
                break;
            case "todo":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'todo (task name)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                Todo newTodo = new Todo(inputWords[1], 'T');
                tasksList.addToTasksList(newTodo);
                tasksList.printAddTaskText(newTodo);
                taskNumber = tasksList.getTasksListSize() - 1;
                loadTasktoDataFile(taskNumber);
                break;
            case "deadline":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'deadline (task name) /by (deadline)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                String[] DescriptionWithTime = inputWords[1].split("/by ", 2);
                Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
                tasksList.addToTasksList(newDeadlineTask);
                tasksList.printAddTaskText(newDeadlineTask);
                taskNumber = tasksList.getTasksListSize() - 1;
                loadTasktoDataFile(taskNumber);
                break;
            case "event":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'event (task name) /by (event date)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                DescriptionWithTime = inputWords[1].split("/at ", 2);
                Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
                tasksList.addToTasksList(newEvent);
                tasksList.printAddTaskText(newEvent);
                taskNumber = tasksList.getTasksListSize() - 1;
                loadTasktoDataFile(taskNumber);
                break;
            default:
                throw new EmptyArgumentException();
            }
        }
    }
}
