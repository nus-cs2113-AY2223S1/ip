package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    public static final String DEADLINE_INFO_SEPARATOR = "/by";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int CAPACITY = 100;

    private static final String INTRO_MESSAGE = "Hello! I'm Duke";
    public static final String QUERY_COMMAND_MESSAGE = "What can I do for you?";
    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String MARK_TASK_UNDONE_MESSAGE = "Nice! I've marked this task as not done:";
    public static final String MARK_TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String EVENT_INFO_SEPARATOR = "/at";

    private static Task[] tasks;

    private static String commandWord;
    private static String commandInfo;
    private static String description;
    private static String time;

    public static void main(String[] args) {
        initTaskList();
        printWelcomeMessage();
        while (true) {
            String inputString = getUserInput();
            try {
                executeCommand(inputString);
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getExceptionDescription());
            } catch (InvalidCommandException e) {
                System.out.println(e.getExceptionDescription());
            }
        }
    }
    private static void executeCommand(String inputString) throws EmptyDescriptionException, InvalidCommandException {
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
            createTodo();
            break;
        case "deadline":
            handleCreateDeadlineCommand();
            break;
        case "event":
            handleCreateEventCommand();
            break;
        case "bye":
            exitProgramme();
        default:
            throw new InvalidCommandException();
        }
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

    private static void handleCreateEventCommand() throws EmptyDescriptionException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("event");
        }
        getDescriptionAndTime(EVENT_INFO_SEPARATOR);
        createEvent();
    }

    private static void createEvent() {
        Event newEvent = new Event(description, time);
        tasks[Task.getTaskCount() - 1] = newEvent;
        newEvent.echo();
    }


    private static void handleCreateDeadlineCommand() throws EmptyDescriptionException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("deadline");
        }
        getDescriptionAndTime(DEADLINE_INFO_SEPARATOR);
        createDeadline();
    }

    private static void createDeadline() {
        Deadline newDeadline = new Deadline(description, time);
        tasks[Task.getTaskCount() - 1] = newDeadline;
        newDeadline.echo();
    }

    private static void createTodo() throws EmptyDescriptionException {
        if (commandInfo.length() == 0) {
            throw new EmptyDescriptionException("todo");
        }
        Todo newTodo = new Todo(commandInfo);
        tasks[Task.getTaskCount() -1] = newTodo;
        newTodo.echo();
    }
    private static void getDescriptionAndTime(String separator) {
        description = getSubStringBeforeSeparator(commandInfo, separator).trim();
        time = getSubStringAfterSeparator(commandInfo, separator, 2).trim();
    }

    private static void changeTaskStatus(boolean isMarkAsDone) {
        int taskIndex = Integer.parseInt(commandInfo) - 1;
        Task targetTask = tasks[taskIndex];
        if (isMarkAsDone) {
            targetTask.setIsDone(true);
            System.out.println(MARK_TASK_DONE_MESSAGE);
        } else {
            targetTask.setIsDone(false);
            System.out.println(MARK_TASK_UNDONE_MESSAGE);
        }
        System.out.println("  " + targetTask.getTaskInfo());
    }

    private static void exitProgramme() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void initTaskList() {
        tasks = new Task[CAPACITY];
    }
    private static void printWelcomeMessage() {
        System.out.println(INTRO_MESSAGE);
        System.out.println(QUERY_COMMAND_MESSAGE);
    }
    private static String getUserInput() {
        return SCANNER.nextLine();
    }


    private static void listTasks() {
        System.out.println(PRINT_LIST_MESSAGE);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.printf("%d.%s" + System.lineSeparator() , i + 1, tasks[i].getTaskInfo());
        }
    }

}
