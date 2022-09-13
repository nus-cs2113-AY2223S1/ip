package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String DEADLINE_INFO_SEPARATOR = "/by";
    public static final String DUKE_TXT = "data/duke.txt";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INTRO_MESSAGE = "Hello! I'm Duke";
    public static final String QUERY_COMMAND_MESSAGE = "What can I do for you?";
    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String MARK_TASK_UNDONE_MESSAGE = "Nice! I've marked this task as not done:";
    public static final String MARK_TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String EVENT_INFO_SEPARATOR = "/at";

    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String commandWord;
    private static String commandInfo;
    private static String description;
    private static String time;

    public static void main(String[] args) {
        try {
            readDukeTxt();
        } catch (IOException e) {
            System.out.println("Error occurred when reading duke txt");
        }
        printWelcomeMessage();
        while (true) {
            String inputString = getUserInput();
            try {
                executeCommand(inputString);
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getExceptionDescription());
            } catch (InvalidCommandException e) {
                System.out.println(e.getExceptionDescription());
            } catch (IOException e) {
            System.out.println("Error writing to duke txt");
            }
        }
    }

    private static void readDukeTxt() throws IOException {
        File dukeTxt = new File(DUKE_TXT);
        File dukeTxtFolder = new File (dukeTxt.getParentFile().getAbsolutePath());
        dukeTxtFolder.mkdir();
        dukeTxt.createNewFile();

        Scanner taskListScanner = new Scanner(dukeTxt);
        while (taskListScanner.hasNext()) {
            String taskString = taskListScanner.nextLine();
            int FirstSeparatorIndex = taskString.indexOf("|");
            int SecondSeparatorIndex = taskString.indexOf("|", FirstSeparatorIndex + 1);
            boolean isDone = Integer.parseInt(taskString.substring(FirstSeparatorIndex + 1, SecondSeparatorIndex).trim()) == 1;
            commandInfo = taskString.substring(SecondSeparatorIndex + 1).trim();
            if (taskString.startsWith("T")) {
                Todo addedToDo = new Todo(commandInfo);
                tasks.add(addedToDo);
            } else if (taskString.startsWith("D")) {
                int ThirdSeparatorIndex = commandInfo.indexOf("|");
                description = commandInfo.substring(0, ThirdSeparatorIndex).trim();
                time = commandInfo.substring(ThirdSeparatorIndex + 1).trim();
                Deadline addedDeadline = new Deadline(description, time);
                tasks.add(addedDeadline);
            } else if (taskString.startsWith("E")) {
                int ThirdSeparatorIndex = commandInfo.indexOf("|");
                description = commandInfo.substring(0, ThirdSeparatorIndex).trim();
                time = commandInfo.substring(ThirdSeparatorIndex + 1).trim();
                Event addedEvent = new Event(description, time);
                tasks.add(addedEvent);
            }
            if (isDone) {
                tasks.get(tasks.size() - 1).setIsDone(true);
            }
        }
    }
    private static void executeCommand(String inputString) throws EmptyDescriptionException, InvalidCommandException, IOException {
        separateCommandWordAndCommandInfo(inputString);
        switch (commandWord) {
        case "list":
            listTasks();
            break;
        case "mark":
            changeTaskStatus(true);
            break;
        case "unmark":
            changeTaskStatus(false);
            break;
        case "todo":
            createToDo();
            break;
        case "deadline":
            handleCreateDeadlineCommand();
            break;
        case "event":
            handleCreateEventCommand();
            break;
        case "delete":
            handleDelete();
            break;
        case "bye":
            exitProgramme();
        default:
            throw new InvalidCommandException();
        }
    }
    private static void overWriteDukeTxt() throws IOException {
        String newText = generateNewText();
        writeToFile(newText);
    }

    private static void handleDelete() throws IOException {
        int taskIndex = Integer.parseInt(commandInfo) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskIndex).getTaskInfo());
        tasks.remove(taskIndex);
        printTasksCount();
        overWriteDukeTxt();
    }

    private static String generateNewText() {
        String newText = "";
        for (Task task : tasks) {
            newText += task.toString();
        }
        return newText;
    }

    private static void writeToFile(String textToAdd) throws IOException {
            FileWriter fileWriter = new FileWriter(Duke.DUKE_TXT);
            fileWriter.write(textToAdd);
            fileWriter.close();
    }
    private static void appendToFile(String textToAppend) throws IOException {
            FileWriter fileWriter = new FileWriter(Duke.DUKE_TXT, true); // create a FileWriter in append mode
            fileWriter.write(textToAppend);
            fileWriter.close();
    }
    private static void separateCommandWordAndCommandInfo(String inputString) {
        commandWord = getSubStringBeforeSeparator(inputString, " ");
        commandInfo = getSubStringAfterSeparator(inputString, " ", 0);
    }

    private static String getSubStringAfterSeparator(String inputString, String separator, int additionalIndex) {
        int separatorIndex = inputString.indexOf(separator);
        if (separatorIndex == -1) {
            return "";
        }
        return inputString.substring(separatorIndex + 1 + additionalIndex);
    }


    private static String getSubStringBeforeSeparator(String inputString, String separator) {
        int separatorIndex = inputString.indexOf(separator);
        if (separatorIndex == -1) {
            return inputString;
        }
        return inputString.substring(0,separatorIndex);
    }

    private static void handleCreateEventCommand() throws EmptyDescriptionException, IOException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("event");
        }
        getDescriptionAndTime(EVENT_INFO_SEPARATOR);
        createEvent();
    }

    private static void createEvent() throws IOException {
        Event newEvent = new Event(description, time);
        tasks.add(newEvent);
        newEvent.echo();
        printTasksCount();
        appendToFile(newEvent.toString());
    }


    private static void handleCreateDeadlineCommand() throws EmptyDescriptionException, IOException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("deadline");
        }
        getDescriptionAndTime(DEADLINE_INFO_SEPARATOR);
        createDeadline();
    }

    private static void createDeadline() throws IOException {
        Deadline newDeadline = new Deadline(description, time);
        tasks.add(newDeadline);
        newDeadline.echo();
        printTasksCount();
        appendToFile(newDeadline.toString());
    }

    private static void createToDo() throws EmptyDescriptionException, IOException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("todo");
        }
        Todo newToDo = new Todo(commandInfo);
        tasks.add(newToDo);
        newToDo.echo();
        printTasksCount();
        appendToFile(newToDo.toString());
    }
    private static void getDescriptionAndTime(String separator) {
        description = getSubStringBeforeSeparator(commandInfo, separator).trim();
        time = getSubStringAfterSeparator(commandInfo, separator, 2).trim();
    }

    private static void changeTaskStatus(boolean isMarkAsDone) throws IOException {
        int taskIndex = Integer.parseInt(commandInfo) - 1;
        Task targetTask = tasks.get(taskIndex);
        if (isMarkAsDone) {
            targetTask.setIsDone(true);
            System.out.println(MARK_TASK_DONE_MESSAGE);
        } else {
            targetTask.setIsDone(false);
            System.out.println(MARK_TASK_UNDONE_MESSAGE);
        }
        System.out.println("  " + targetTask.getTaskInfo());
        overWriteDukeTxt();
    }

    private static void exitProgramme() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }


    private static void printWelcomeMessage() {
        System.out.println(INTRO_MESSAGE);
        System.out.println(QUERY_COMMAND_MESSAGE);
    }
    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static void printTasksCount() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    private static void listTasks() {
        System.out.println(PRINT_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s" + System.lineSeparator() , i + 1, tasks.get(i).getTaskInfo());
        }
    }

}
