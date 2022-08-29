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
            executeCommand(inputString);
        }
    }

    private static void executeCommand(String inputString) {
        separateCommandWordAndCommandInfo(inputString);
        switch (commandWord) {
        case "list":
            listTasks();
            break;
        case "mark":
            changeTaskStatus(commandInfo, true);
            break;
        case "unmark":
            changeTaskStatus(commandInfo, false);
            break;
        case "todo":
            createTodo(commandInfo);
            break;
        case "deadline":
            handleCreateDeadlineCommand(commandInfo);
            break;
        case "event":
            handleCreateEventCommand(commandInfo);
            break;
        case "bye":
            exitProgramme();
        default:
            createTask(inputString);
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

    private static void handleCreateEventCommand(String inputString) {
        getDescriptionAndTime(inputString, EVENT_INFO_SEPARATOR);
        createEvent();
    }

    private static void createEvent() {
        Event newEvent = new Event(description, time);
        tasks[Task.getTaskCount() - 1] = newEvent;
        newEvent.echo();
    }


    private static void handleCreateDeadlineCommand(String commandInfo) {
        getDescriptionAndTime(commandInfo, DEADLINE_INFO_SEPARATOR);
        createDeadline();
    }

    private static void createDeadline() {
        Deadline newDeadline = new Deadline(description, time);
        tasks[Task.getTaskCount() - 1] = newDeadline;
        newDeadline.echo();
    }

    private static void createTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks[Task.getTaskCount() -1] = newTodo;
        newTodo.echo();
    }
    private static void getDescriptionAndTime(String commandInfo, String separator) {
        description = getSubStringBeforeSeparator(commandInfo, separator).trim();
        time = getSubStringAfterSeparator(commandInfo, separator, 2).trim();
    }

    private static void changeTaskStatus(String commandInfo, boolean isMarkAsDone) {
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

    private static void createTask(String input) {
        Task newTask = new Task(input);
        tasks[Task.getTaskCount() - 1] = newTask;
        newTask.echo();
    }

}
