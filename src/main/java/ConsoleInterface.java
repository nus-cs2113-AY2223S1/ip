import java.util.Scanner;

public class ConsoleInterface {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private Scanner scanner;
    private TaskManager taskManager;

    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        taskManager = new TaskManager();
    }

    /**
     * Prints logo to standard out.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints line separator to standard out.
     */
    public static void printLineSeparator() {
        String lineSeparator = "____________________________________________________________";
        System.out.println(lineSeparator);
    }

    /**
     * Prints greeting message to standard out.
     */
    public static void printGreetingMessage() {
        String greetingMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(greetingMessage);
    }

    /**
     * Prints goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    /**
     * Reads user input from standard in.
     *
     * @return Command and arguments from the user.
     */
    public ConsoleInput getConsoleInput() {
        String userInputStr = scanner.nextLine();
        String[] userInputArr = userInputStr.split(" ", 2);
        String command = userInputArr[0];
        String argument = "";
        if (userInputArr.length == 2) {
            argument = userInputArr[1];
        }

        ConsoleInput consoleInput = new ConsoleInput(command, argument);
        return consoleInput;
    }

    /**
     * Prints all tasks in task manager to standard out.
     */
    public void executeCommandList() {
        System.out.println("Here are the tasks in your list:");

        taskManager.printTasks();
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumberStr Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandMark(String taskNumberStr) {
        int taskNumberInt = Integer.parseInt(taskNumberStr);
        taskManager.markTaskAsCompleted(taskNumberInt);
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskNumberStr Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandUnmark(String taskNumberStr) {
        int taskNumberInt = Integer.parseInt(taskNumberStr);
        taskManager.markTaskAsUncompleted(taskNumberInt);
    }

    /**
     * Adds a todo into task manager.
     *
     * @param arguments Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandTodo(String arguments) {
        System.out.println("Got it. I've added this task:");

        Todo todo = new Todo(arguments);
        taskManager.addTask(todo);
        todo.print();

        int numTasks = taskManager.getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Adds a deadline into task manager.
     *
     * @param arguments Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandDeadline(String arguments) {
        System.out.println("Got it. I've added this task:");

        String[] argumentArray = arguments.split("/by");
        String description = argumentArray[0].trim();
        String by = argumentArray[1].trim();

        Deadline deadline = new Deadline(description, by);
        taskManager.addTask(deadline);
        deadline.print();

        int numTasks = taskManager.getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Adds an event into task manager.
     *
     * @param arguments Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandEvent(String arguments) {
        System.out.println("Got it. I've added this task:");

        String[] argumentArray = arguments.split("/at");
        String description = argumentArray[0].trim();
        String at = argumentArray[1].trim();

        Event event = new Event(description, at);
        taskManager.addTask(event);
        event.print();

        int numTasks = taskManager.getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Executes main interface which interacts with user
     */
    public void executeProgram() {
        while (true) {
            System.out.println();

            ConsoleInput input = getConsoleInput();

            if (input.getCommand().equals(COMMAND_BYE)) {
                break;
            }

            ConsoleInterface.printLineSeparator();

            switch (input.getCommand()) {
            case COMMAND_LIST:
                executeCommandList();
                break;
            case COMMAND_MARK:
                executeCommandMark(input.getArguments());
                break;
            case COMMAND_UNMARK:
                executeCommandUnmark(input.getArguments());
                break;
            case COMMAND_TODO:
                executeCommandTodo(input.getArguments());
                break;
            case COMMAND_DEADLINE:
                executeCommandDeadline(input.getArguments());
                break;
            case COMMAND_EVENT:
                executeCommandEvent(input.getArguments());
                break;
            default:
                break;
            }

            ConsoleInterface.printLineSeparator();
        }
    }
}
