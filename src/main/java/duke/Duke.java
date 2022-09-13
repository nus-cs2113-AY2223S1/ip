package duke;

import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingDateTimeReferenceException;
import duke.exception.MissingListIndexException;
import duke.exception.UndefinedCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static String readTaskDescription(Scanner in) throws EmptyTaskDescriptionException {
        String taskDescription = in.nextLine().trim();
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        return taskDescription;
    }

    public static String extractTaskName(String taskDescription, String dateTimeReference)
            throws MissingDateTimeReferenceException {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        boolean haveDataTimeReference = (dateTimeIndex != -1);
        if (!haveDataTimeReference) {
            throw new MissingDateTimeReferenceException();
        }
        return taskDescription.substring(0, dateTimeIndex - 1).trim();
    }

    public static String extractTaskDateTime(String taskDescription, String dateTimeReference)
            throws MissingDateTimeReferenceException {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        boolean haveDataTimeReference = (dateTimeIndex != -1);
        if (!haveDataTimeReference) {
            throw new MissingDateTimeReferenceException();
        }
        return taskDescription.substring(dateTimeIndex + 3).trim();
    }

    public static void addToDoTask(ArrayList<Task> taskList, String todoTaskName) {
        taskList.add(new Todo(todoTaskName));
    }

    public static void addDeadlineTask(ArrayList<Task> taskList, String deadlineTaskName, String deadlineTaskBy) {
        taskList.add(new Deadline(deadlineTaskName, deadlineTaskBy));
    }

    public static void addEventTask(ArrayList<Task> taskList, String eventTaskName, String eventTaskAt) {
        taskList.add(new Event(eventTaskName, eventTaskAt));
    }

    public static void printTaskAddedMessage(ArrayList<Task> taskList, int maxTaskIndex) {
        boolean isSingleTask = (maxTaskIndex == 0);
        String task = isSingleTask ? " task" : " tasks";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(maxTaskIndex));
        System.out.println("Now you have " + (maxTaskIndex + 1) + task + " in the list.");
    }

    public static void markAsDone(ArrayList<Task> taskList, Scanner in) {
        try {
            String markTaskIndex = readTaskIndex(in);
            int markIndex = Integer.parseInt(markTaskIndex) - 1;
            taskList.get(markIndex).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.get(markIndex));
            updateDukeTextFile(markIndex, true);
        } catch (MissingListIndexException e) {
            showMissingMarkIndexMessage();
        } catch (IndexOutOfBoundsException e) {
            showIndexOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            showNumberFormatExceptionMessage();
        } catch (IOException e) {
            showIOExceptionMessage();
        }
    }

    public static void markAsUndone(ArrayList<Task> taskList, Scanner in) {
        try {
            String unmarkTaskIndex = readTaskIndex(in);
            int unmarkIndex = Integer.parseInt(unmarkTaskIndex) - 1;
            taskList.get(unmarkIndex).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + taskList.get(unmarkIndex));
            updateDukeTextFile(unmarkIndex, false);
        } catch (MissingListIndexException e) {
            showMissingUnmarkIndexMessage();
        } catch (IndexOutOfBoundsException e) {
            showIndexOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            showNumberFormatExceptionMessage();
        } catch (IOException e) {
            showIOExceptionMessage();
        }
    }

    public static void printList(ArrayList<Task> taskList, int maxTaskIndex) {
        boolean hasNoTask = (maxTaskIndex == 0);
        if (hasNoTask) {
            System.out.println("There is no task in your list.");
            return;
        }
        boolean isSingleTask = (maxTaskIndex == 1);
        String taskString = isSingleTask ? "is the task" : "are the tasks";
        System.out.println("Here " + taskString + " in your list:");
        int taskIndex = 1;
        for (Task task : taskList) {
            System.out.println((taskIndex) + "." +  task);
            taskIndex++;
        }
    }

    public static String readTaskIndex(Scanner in) throws MissingListIndexException {
        String taskIndex = in.nextLine().trim();
        if (taskIndex.isEmpty()) {
            throw new MissingListIndexException();
        }
        return taskIndex;
    }

    private static void findDataFile(File dataFile) throws SecurityException {
        boolean hasCreatedDataFile = dataFile.mkdir();
        if (hasCreatedDataFile) {
            System.out.println("'data' directory has been created as it does not exist.");
        }
    }

    private static void findDukeTextFile(File fileDuke) throws IOException {
        boolean hasCreatedDukeFile = fileDuke.createNewFile();
        if (hasCreatedDukeFile) {
            System.out.println("'duke.txt' has been created as it does not exist.");
        }
    }

    private static int loadDukeTextFile(ArrayList<Task> taskList, File dukeFile) throws FileNotFoundException {
        Scanner dukeScanner = new Scanner(dukeFile);
        String taskDescription;
        String dateTime;
        int descriptionPartitionIndex;
        int taskIndex = 0;
        boolean isMarked;

        while (dukeScanner.hasNext()) {
            taskDescription = dukeScanner.nextLine().trim();
            char taskType = taskDescription.charAt(0);
            char marked = taskDescription.charAt(4);
            taskDescription = taskDescription.substring(8);
            String taskName;

            switch (taskType) {
            case 'T':
                taskName = taskDescription.trim();
                addToDoTask(taskList, taskName);
                break;
            case 'D':
                descriptionPartitionIndex = taskDescription.indexOf("|");
                taskName = taskDescription.substring(0, descriptionPartitionIndex - 1).trim();
                dateTime = taskDescription.substring(descriptionPartitionIndex + 1).trim();
                addDeadlineTask(taskList, taskName, dateTime);
                break;
            case 'E':
                descriptionPartitionIndex = taskDescription.indexOf("|");
                taskName = taskDescription.substring(0, descriptionPartitionIndex - 1).trim();
                dateTime = taskDescription.substring(descriptionPartitionIndex + 1).trim();
                addEventTask(taskList, taskName, dateTime);
                break;
            default:
            }
            isMarked = (marked == '1');
            taskList.get(taskIndex).setDone(isMarked);

            taskIndex++;
        }
        return taskIndex;
    }

    public static boolean deleteTask(ArrayList<Task> taskList, Scanner in, int maxTaskIndex) {
        boolean isEmptyList = (maxTaskIndex == 0);
        if (isEmptyList) {
            System.out.println("☹ OOPS!!! There is no task in the list.");
            return false;
        }

        try {
            String deleteTaskIndex = readTaskIndex(in);
            int deleteIndex = Integer.parseInt(deleteTaskIndex) - 1;
            String deletedTaskDescription = String.valueOf(taskList.get(deleteIndex));
            taskList.remove(deleteIndex);

            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deletedTaskDescription);
            maxTaskIndex--;
            boolean isEmptyListOrSingleTask = (maxTaskIndex == 0 || maxTaskIndex == 1);
            String task = isEmptyListOrSingleTask ? "task" : "tasks";
            System.out.println("Now you have " + maxTaskIndex + " " + task + " in the list.");

            updateDukeTextFile(deleteIndex, null);

            return true;
        } catch (MissingListIndexException e) {
            showMissingDeleteIndexMessage();
        } catch (IndexOutOfBoundsException e) {
            showIndexOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            showNumberFormatExceptionMessage();
        } catch (IOException e) {
            showIOExceptionMessage();
        }
        return false;
    }

    public static void updateDukeTextFile(int lineToEdit, Boolean isMarked) throws IOException {
        File dukeFile = new File("data/duke.txt");
        Scanner dukeFileScanner = new Scanner(dukeFile);
        StringBuilder taskDescriptions = new StringBuilder();
        int lineNumber = 0;

        while (dukeFileScanner.hasNext()) {
            String currentLine = dukeFileScanner.nextLine();
            if (lineNumber != lineToEdit) {
                taskDescriptions.append(currentLine).append(System.lineSeparator());
            } else if (isMarked != null) {
                taskDescriptions.append(updateMarkStatus(isMarked, currentLine)).append(System.lineSeparator());
            }
            lineNumber++;
        }

        FileWriter dukeFileWriter = new FileWriter("data/duke.txt");
        dukeFileWriter.write(taskDescriptions.toString());
        dukeFileWriter.close();
    }

    public static String updateMarkStatus(boolean isMarked, String currentLine) {
        String markStatus = isMarked ? "1" : "0";
        return currentLine.substring(0, 4) + markStatus + currentLine.substring(5);
    }

    public static void addTaskToDukeTextFile(String addedTaskDescription) throws IOException {
        File dukeFile = new File("data/duke.txt");
        Scanner dukeFileScanner = new Scanner(dukeFile);
        String newDukeTextFileContent = copyFileContent(dukeFileScanner);
        newDukeTextFileContent += addedTaskDescription + System.lineSeparator();
        FileWriter dukeFileWriter = new FileWriter("data/duke.txt");
        dukeFileWriter.write(newDukeTextFileContent);
        dukeFileWriter.close();
    }

    public static String copyFileContent(Scanner dukeFileScanner) {
        StringBuilder taskDescriptions = new StringBuilder();
        while (dukeFileScanner.hasNext()) {
            String currentLine = dukeFileScanner.nextLine();
            taskDescriptions.append(currentLine).append(System.lineSeparator());
        }
        return taskDescriptions.toString();
    }

    public static void main(String[] args) {
        //Attempt to find 'data' directory; Directory created if not found
        File dataFile = new File("data");
        try {
            findDataFile(dataFile);
        } catch (SecurityException e) {
            showSecurityExceptionMessage();
        }

        //Attempt to find 'duke.txt' text file; Text file created if not found
        File dukeFile = new File("data/duke.txt");
        try {
            findDukeTextFile(dukeFile);
        } catch (IOException e) {
            showIOExceptionMessage();
        }

        ArrayList<Task> taskList = new ArrayList<>();
        int maxTaskIndex = 0;

        //Load task descriptions (if any) into list
        try {
            maxTaskIndex = loadDukeTextFile(taskList ,dukeFile);
        } catch (FileNotFoundException e) {
            showDukeTextFileNotFoundMessage();
        }

        //Greet
        showWelcomeMessage();

        //Read Input
        Scanner in = new Scanner(System.in);    //create object that reads input
        String command = in.next();             //variable to store line (input)

        while (!command.equals("bye")) {
            switch (command) {
            case "list":
                printList(taskList, maxTaskIndex);
                break;
            case "unmark":
                markAsUndone(taskList, in);
                break;
            case "mark":
                markAsDone(taskList, in);
                break;
            //Add Task
            case "todo":
                try {
                    String todoTaskName = readTaskDescription(in);
                    addToDoTask(taskList, todoTaskName);
                    printTaskAddedMessage(taskList, maxTaskIndex);
                    addTaskToDukeTextFile("T | 0 | " + todoTaskName);
                    maxTaskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyToDoDescriptionExceptionMessage();
                } catch (IOException e) {
                    showIOExceptionMessage();
                }
                break;
            case "deadline":
                try {
                    String deadlineTask = readTaskDescription(in);
                    String deadlineTaskName = extractTaskName(deadlineTask ,"/by");
                    String deadlineTaskBy   = extractTaskDateTime(deadlineTask ,"/by");
                    addDeadlineTask(taskList, deadlineTaskName, deadlineTaskBy);
                    printTaskAddedMessage(taskList, maxTaskIndex);
                    addTaskToDukeTextFile("D | 0 | " + deadlineTaskName + " | " + deadlineTaskBy);
                    maxTaskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyDeadlineDescriptionExceptionMessage();
                } catch (MissingDateTimeReferenceException e) {
                    showMissingDeadlineDateTimeReferenceExceptionMessage();
                } catch (IOException e) {
                    showIOExceptionMessage();
                }
                break;
            case "event":
                try {
                    String eventTask = readTaskDescription(in);
                    String eventTaskName = extractTaskName(eventTask ,"/at");
                    String eventTaskAt   = extractTaskDateTime(eventTask ,"/at");
                    addEventTask(taskList, eventTaskName, eventTaskAt);
                    printTaskAddedMessage(taskList, maxTaskIndex);
                    addTaskToDukeTextFile("E | 0 | " + eventTaskName + " | " + eventTaskAt);
                    maxTaskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyEventDescriptionExceptionMessage();
                } catch (MissingDateTimeReferenceException e) {
                    showMissingEventDateTimeReferenceExceptionMessage();
                } catch (IOException e) {
                    showIOExceptionMessage();
                }
                break;
            case "delete":
                boolean isDeleted = deleteTask(taskList, in, maxTaskIndex);
                if (isDeleted) {
                    maxTaskIndex--;
                }
                break;
            default:
                showUndefinedCommandMessage();
            }
            command = in.next();                //Read first word (command)
        }

        //Exit
        showByeMessage();
    }

    private static void showMissingMarkIndexMessage() {
        System.out.println("☹ OOPS!!! To mark a task, please specify the task index.\nPlease try again.");
    }

    private static void showMissingUnmarkIndexMessage() {
        System.out.println("☹ OOPS!!! To unmark a task, please specify the task index.\nPlease try again.");
    }

    private static void showMissingDeleteIndexMessage() {
        System.out.println("☹ OOPS!!! To delete a task, please specify the task index.\nPlease try again.");
    }

    private static void showNumberFormatExceptionMessage() {
        System.out.println("☹ OOPS!!! Please kindly provide a valid task index. (numerical/exist)");
    }

    private static void showIndexOutOfBoundsExceptionMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, the task index does not exist in the task list.");
    }

    private static void showDukeTextFileNotFoundMessage() {
        System.out.println("duke.txt cannot be found");
    }

    private static void showIOExceptionMessage() {
        System.out.println("IO Exception Triggered");
    }

    private static void showSecurityExceptionMessage() {
        System.out.println("Security Exception Triggered");
    }

    private static void showMissingDeadlineDateTimeReferenceExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a deadline requires a specific date/time denoted after '/by'.");
        System.out.println("Example: deadline return book /by Sunday");
        System.out.println("Please try again.");
    }

    private static void showMissingEventDateTimeReferenceExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a event requires a date and specific start & end time " +
                "denoted after '/at'.");
        System.out.println("Example: event project meeting /at Mon 2-4pm");
        System.out.println("Please try again.");
    }

    private static void showEmptyToDoDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    private static void showEmptyDeadlineDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    private static void showEmptyEventDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
    }

    private static void showUndefinedCommandMessage() {
        try {
            throw new UndefinedCommandException();
        } catch (UndefinedCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void showByeMessage() {
        System.out.println("{\\__/}");
        System.out.println("(´^ω^)ノ Bye. Hope to see you again soon!");
        System.out.println("/ v v/");
    }

    private static void showWelcomeMessage() {

        String logo = "          @@@@@@@@@@@@@@@@%\n"
                + "       @@@%%%%%%%%%%%%%%%%%&@@\n"
                + "       @@%%%%%%%%%%###%#####%@@@@@@\n"
                + "        @@%%%%%##%@@@&@###@##@##@##@@\n"
                + "         @@@@&@####@###%@##@#@##&&#@@\n"
                + "        @@%@%%%@@##@@@@@@@@@@#/**@@@\n"
                + "         @@%@@@@@#*****************/@@\n"
                + "          /@@************#@@@@@@@@@@@@@@\n"
                + "          @@********%@@(******         *%@@\n"
                + "         @@(*******@@###*****            **@@\n"
                + "   @@@@@@@@((******@#####*****          ****@\n"
                + ",@@******@%((******@@######**************###@%\n"
                + "@@******%@((((******@@#####################@@\n"
                + "@@**/((((@((((********@@%###############&@@\n"
                + "@@(((((((@(((((***********(@@@@@@@@@&/**&@@\n"
                + "@@(((((((@(((((**************************@@\n"
                + "@@(((((((@#((((**************************@@\n"
                + "@@(((((((@&(((((*************************@@\n"
                + "@@(((((((@@((((((**********************((@@\n"
                + "@@(((((((@@(((((((((***************/(((((@/\n"
                + "@@(((((((@@(((((((((((((((((((((((((((((&@\n"
                + " @@((((((@@(((((((((((((((((((((((((((((@@\n"
                + "   @@@@@@@@((((((((((((@@@@@@@@@@@&(((((@@\n"
                + "         @@((((((((((((@%   @@((((((((((@@\n"
                + "         @@((((((((((((@    *@((((((((((@/\n"
                + "         %@(((((((((((@@     @%((((((((@@\n"
                + "         .@@((((((((((@@     @@@@@@@@@@.\n"
                + "             #@@@@@@*";

        //Greet
        System.out.println("Hello I'm Duke\n" + logo);
        System.out.println("What can I do for you?");
    }
}
