package duke;

import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingDateTimeReferenceException;
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


public class Duke {
    static final int MAX_TASK_SIZE = 100;

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

    public static void addToDoTask(Task[] tasks, int taskIndex, String todoTaskName) {
        tasks[taskIndex] = new Todo(todoTaskName);
    }

    public static void addDeadlineTask(Task[] tasks, int taskIndex, String deadlineTaskName, String deadlineTaskBy) {
        tasks[taskIndex] = new Deadline(deadlineTaskName, deadlineTaskBy);
    }

    public static void addEventTask(Task[] tasks, int taskIndex, String eventTaskName, String eventTaskAt) {
        tasks[taskIndex] = new Event(eventTaskName, eventTaskAt);
    }

    public static void printTaskAddedMessage(Task[] tasks, int taskIndex) {
        boolean isSingleTask = (taskIndex == 0);
        String task = isSingleTask ? " task" : " tasks";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskIndex]);
        System.out.println("Now you have " + (taskIndex + 1) + task + " in the list.");
    }

    public static void markAsDone(Task[] tasks, Scanner in) {
        try {
            int markIndex = Integer.parseInt(in.next()) - 1;
            tasks[markIndex].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[markIndex]);
            updateMarkStatusInDukeTextFile(markIndex, true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, the taskID does not exist in the task list.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, there is no such task found in the task list.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please kindly provide a numerical taskID.");
        } catch (IOException e) {
            showIOExceptionMessage();
        }
    }

    public static void markAsUndone(Task[] tasks, Scanner in) {
        try {
            int unmarkIndex = Integer.parseInt(in.next()) - 1;
            tasks[unmarkIndex].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[unmarkIndex]);
            updateMarkStatusInDukeTextFile(unmarkIndex, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, the taskID does not exist in the task list.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, there is no such task found in the task list.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please kindly provide a numerical taskID.");
        } catch (IOException e) {
            showIOExceptionMessage();
        }
    }

    public static void printList(Task[] tasks, int taskIndex) {
        boolean hasNoTask = (taskIndex == 0);
        if (hasNoTask) {
            System.out.println("There is no task in your list.");
            return;
        }
        boolean isSingleTask = (taskIndex == 1);
        String task = isSingleTask ? "is the task" : "are the tasks";
        System.out.println("Here " + task + " in your list:");
        taskIndex = 0;
        while (tasks[taskIndex] != null) {
            System.out.println((taskIndex + 1) + "." + tasks[taskIndex]);
            taskIndex++;
        }
    }

    private static void findDataFile(File dataFile) throws SecurityException {
        boolean hasFileData = dataFile.mkdir();
        if (!hasFileData) {
            System.out.println("'data' directory has been created as it does not exist.");
        }
    }

    private static void findDukeTextFile(File fileDuke) throws IOException {
        boolean hasCreateFile = fileDuke.createNewFile();
        if (hasCreateFile) {
            System.out.println("'duke.txt' has been created as it does not exist.");
        }
    }

    private static int loadDukeTextFile(Task[] tasks, File dukeFile) throws FileNotFoundException {
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
                addToDoTask(tasks, taskIndex, taskName);
                break;
            case 'D':
                descriptionPartitionIndex = taskDescription.indexOf("|");
                taskName = taskDescription.substring(0, descriptionPartitionIndex - 1).trim();
                dateTime = taskDescription.substring(descriptionPartitionIndex + 1).trim();
                addDeadlineTask(tasks, taskIndex, taskName, dateTime);
                break;
            case 'E':
                descriptionPartitionIndex = taskDescription.indexOf("|");
                taskName = taskDescription.substring(0, descriptionPartitionIndex - 1).trim();
                dateTime = taskDescription.substring(descriptionPartitionIndex + 1).trim();
                addEventTask(tasks, taskIndex, taskName, dateTime);
                break;
            default:
            }
            isMarked = (marked == '1');
            tasks[taskIndex].setDone(isMarked);

            taskIndex++;
        }
        return taskIndex;
    }

    public static void updateMarkStatusInDukeTextFile(int lineToEdit, boolean isMarked) throws IOException, NullPointerException {
        File dukeFile = new File("data/duke.txt");
        Scanner dukeFileScanner = new Scanner(dukeFile);
        StringBuilder taskDescriptions = new StringBuilder();
        int lineNumber = 0;

        while (dukeFileScanner.hasNext()) {
            String currentLine = dukeFileScanner.nextLine();
            if (lineNumber == lineToEdit) {
                taskDescriptions.append(updateMarkStatus(isMarked, currentLine)).append(System.lineSeparator());
            } else {
                taskDescriptions.append(currentLine).append(System.lineSeparator());
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

    private static String copyFileContent(Scanner dukeFileScanner) {
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

        Task[] tasks = new Task[MAX_TASK_SIZE];
        int taskIndex = 0;

        //Load task descriptions (if any) into list
        try {
            taskIndex = loadDukeTextFile(tasks ,dukeFile);
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
                printList(tasks, taskIndex);
                break;
            case "unmark":
                markAsUndone(tasks, in);
                break;
            case "mark":
                markAsDone(tasks, in);
                break;
            //Add Task
            case "todo":
                try {
                    String todoTaskName = readTaskDescription(in);
                    addToDoTask(tasks, taskIndex, todoTaskName);
                    printTaskAddedMessage(tasks, taskIndex);
                    String addedTaskDescription = "T | 0 | " + todoTaskName;
                    addTaskToDukeTextFile(addedTaskDescription);
                    taskIndex++;
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
                    addDeadlineTask(tasks, taskIndex, deadlineTaskName, deadlineTaskBy);
                    printTaskAddedMessage(tasks, taskIndex);
                    String addedTaskDescription = "D | 0 | " + deadlineTaskName + " | " + deadlineTaskBy;
                    addTaskToDukeTextFile(addedTaskDescription);
                    taskIndex++;
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
                    addEventTask(tasks, taskIndex, eventTaskName, eventTaskAt);
                    printTaskAddedMessage(tasks, taskIndex);
                    String addedTaskDescription = "E | 0 | " + eventTaskName + " | " + eventTaskAt;
                    addTaskToDukeTextFile(addedTaskDescription);
                    taskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyEventDescriptionExceptionMessage();
                } catch (MissingDateTimeReferenceException e) {
                    showMissingEventDateTimeReferenceExceptionMessage();
                } catch (IOException e) {
                    showIOExceptionMessage();
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
