import java.util.Scanner;

public class Duke {
    // ========================================= GLOBAL CONSTANT
    private static final int MAXIMUM_NUMBER_OF_TASKS = 100;

    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO = "Hello! I'm a chatbot Duke made by Than Duc Huy\n" +
            "Type the command to start interacting with Duke";
    private static final String MESSAGE_COMMAND_LISTS = "Supported commands: list, mark, unmark, todo, deadline, event, bye";
    private static final String MESSAGE_DIVIDER = "===================================================";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown Command";

    private static final String ERROR_MESSAGE_BYE = "";
    private static final String ERROR_MESSAGE_LIST = "";
    private static final String ERROR_MESSAGE_MARK = "";
    private static final String ERROR_MESSAGE_UNMARK = "";
    private static final String ERROR_MESSAGE_TODO = "";
    private static final String ERROR_MESSAGE_DEADLINE = "";
    private static final String ERROR_MESSAGE_EVENT = "";

    // ========================================= GLOBAL VARIABLES
    static Scanner in = new Scanner(System.in);
    static Task[] tasksList = new Task[MAXIMUM_NUMBER_OF_TASKS];
    static boolean isRunning = true;

    // ========================================= MAIN
    public static void main(String[] args) {
        helloMessage();
        while (isRunning) {
            parseCommand();
        }
    }

    // ========================================= GLOBAL METHOD
    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    private static void parseCommand() {
        String line = in.nextLine();
        String[] parsedLine = line.split(" ");
        String commandWord = parsedLine[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                exit(parsedLine);
                break;
            case "list":
                list(parsedLine);
                break;
            case "mark":
                mark(parsedLine);
                break;
            case "unmark":
                mark(parsedLine);
                break;
            case "todo":
                add(line);
                break;
            case "deadline":
                add(line);
                break;
            case "event":
                add(line);
                break;
            default:
                unknownCommand();
                break;
        }
    }

    /*
     * private static boolean isCommandBye(String line){
     * return line.toLowerCase().contains("bye");
     * }
     * private static boolean isCommandList(String line){
     * return line.toLowerCase().contains("list");
     * }
     * private static boolean isCommandMark(String line){
     * return line.toLowerCase().contains("mark");
     * }
     * private static boolean isCommandAdd(String line){
     * String lowerCase = line.toLowerCase();
     * return lowerCase.contains("todo") || lowerCase.contains("deadline") ||
     * lowerCase .contains("event");
     * }
     * 
     */

    private static void commandErrorHandler(String errorMessage) {
        switch (errorMessage) {
            case "bye":
                showToUser(ERROR_MESSAGE_BYE);
            case "list":
                showToUser(ERROR_MESSAGE_LIST);
            case "mark":
                showToUser(ERROR_MESSAGE_MARK);
            case "unmark":
                showToUser(ERROR_MESSAGE_UNMARK);
            case "todo":
                showToUser(ERROR_MESSAGE_TODO);
            case "deadline":
                showToUser(ERROR_MESSAGE_DEADLINE);
            case "event":
                showToUser(ERROR_MESSAGE_EVENT);
            default:
                break;
        }
    }

    private static void exit(String[] parsedLine) {
        try {
            if (parsedLine[1] != null) {
                throw new DukeException("bye");
            }
        } catch (DukeException e) {
            commandErrorHandler(e.getMessage());
        }
        isRunning = false;
        exitMessage();
    }

    private static void unknownCommand() {
        showToUser(MESSAGE_UNKNOWN_COMMAND, MESSAGE_COMMAND_LISTS);
    }

    private static void list(String[] parsedLine) {
        try {
            if (parsedLine[1] != null) {
                throw new DukeException("list");
            }
        } catch (DukeException e) {
            commandErrorHandler(e.getMessage());
        }
        showToUser("LIST", MESSAGE_DIVIDER);
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print((i + 1) + ".");
            tasksList[i].printTask();
        }
        showToUser(MESSAGE_DIVIDER);
    }

    private static void list() {
        showToUser("LIST", MESSAGE_DIVIDER);
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print((i + 1) + ".");
            tasksList[i].printTask();
        }
        showToUser(MESSAGE_DIVIDER);
    }

    private static void add(String line) {
        String newTaskType = line.split(" ")[0];
        String[] taskDescriptor = line.replace(newTaskType, "").trim().split("/");
        switch (newTaskType.toLowerCase()) {
            case "todo":
                tasksList[Task.numberOfTasks] = new Todo(taskDescriptor[0]);
                break;
            case "deadline":
                tasksList[Task.numberOfTasks] = new Deadline(taskDescriptor[0], taskDescriptor[1].trim());
                break;
            case "event":
                tasksList[Task.numberOfTasks] = new Event(taskDescriptor[0], taskDescriptor[1].trim());
                break;
            default:
                break;
        }
        addTaskMessage();
    }

    private static void addTaskMessage() {
        System.out.println("Got it! You just add a new Task");
        System.out.print("\t");
        tasksList[Task.numberOfTasks - 1].printTask();
        System.out.println("Number of tasks in the list: " + Task.numberOfTasks);
    }

    public static void mark(String[] parsedLine) {
        int index = Integer.parseInt(parsedLine[1]);
        if (parsedLine[0].equalsIgnoreCase("mark")) {
            tasksList[index - 1].isDone = true;
        } else {
            tasksList[index - 1].isDone = false;
        }
        list();
    }

    private static void exitMessage() {
        showToUser(MESSAGE_DIVIDER, MESSAGE_EXIT, MESSAGE_DIVIDER);
    }

    private static void helloMessage() {
        showToUser(MESSAGE_DIVIDER, MESSAGE_HELLO, MESSAGE_COMMAND_LISTS, MESSAGE_DIVIDER);
    }

}
