package duke.userinterface;

import duke.task.*;

import java.util.Scanner;

/**
 * Provides functions to interface with user via standard input and standard output
 */
public class ConsoleInterface {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";


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
        int numOperands = 2;
        if (userInputArr.length == numOperands) {
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
        int taskNumberInt;
        try {
            taskNumberInt = Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("☹ OOPS!!! Your input " + taskNumberStr + " is not a number.");
            return;
        }

        try {
            taskManager.markTaskAsCompleted(taskNumberInt);
            System.out.println("Nice! I've marked this task as done:");
            System.out.print("  ");
            taskManager.getTask(taskNumberInt).print();
        } catch (TaskManagerException.TaskNotFoundException taskNotFoundException) {
            System.out.println("☹ OOPS!!! Task number " + taskNumberInt + " does not exist.");
            return;
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskNumberStr Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandUnmark(String taskNumberStr) {
        int taskNumberInt;
        try {
            taskNumberInt = Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("☹ OOPS!!! Your input " + taskNumberStr + " is not a number.");
            return;
        }

        try {
            taskManager.markTaskAsUncompleted(taskNumberInt);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print("  ");
            taskManager.getTask(taskNumberInt).print();
        } catch (TaskManagerException.TaskNotFoundException taskNotFoundException) {
            System.out.println("☹ OOPS!!! Task number " + taskNumberInt + " does not exist.");
            return;
        }
    }

    /**
     * Adds a todo into task manager.
     *
     * @param arguments Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandTodo(String arguments) {
        String description = arguments;
        if (description.isEmpty()) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }

        Todo todo = new Todo(description);
        taskManager.addTask(todo);
        System.out.println("Got it. I've added this task:");
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
        if (!arguments.contains("/by")) {
            System.out.println("☹ OOPS!!! The /by option of a deadline must be present.");
            return;
        }

        String[] argumentArray = arguments.split("/by");
        int numOperands = 2;
        if (argumentArray.length != numOperands) {
            System.out.println("☹ OOPS!!! The description and the /by option of a deadline cannot be empty.");
            return;
        }

        String description = argumentArray[0].trim();
        String by = argumentArray[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            System.out.println("☹ OOPS!!! The description and the /by option of a deadline cannot be empty.");
            return;
        }

        Deadline deadline = new Deadline(description, by);
        taskManager.addTask(deadline);
        System.out.println("Got it. I've added this task:");
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
        if (!arguments.contains("/at")) {
            System.out.println("☹ OOPS!!! The /at option of an event must be present.");
            return;
        }

        String[] argumentArray = arguments.split("/at");
        int numOperands = 2;
        if (argumentArray.length != numOperands) {
            System.out.println("☹ OOPS!!! The description and the /at option of an event cannot be empty.");
            return;
        }

        String description = argumentArray[0].trim();
        String at = argumentArray[1].trim();
        if (description.isEmpty() || at.isEmpty()) {
            System.out.println("☹ OOPS!!! The description and the /at option of an event cannot be empty.");
            return;
        }

        Event event = new Event(description, at);
        taskManager.addTask(event);
        System.out.println("Got it. I've added this task:");
        event.print();

        int numTasks = taskManager.getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Deletes a task from task manager.
     *
     * @param taskNumberStr Raw arguments returned by the function {@link #getConsoleInput()}.
     */
    public void executeCommandDelete(String taskNumberStr) {
        int taskNumberInt;
        try {
            taskNumberInt = Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("☹ OOPS!!! Your input " + taskNumberStr + " is not a number.");
            return;
        }

        try {
            Task task = taskManager.deleteTask(taskNumberInt);

            System.out.println("Noted. I've removed this task:");
            task.print();
            int numTasks = taskManager.getNumTasks();
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        } catch (TaskManagerException.TaskNotFoundException taskNotFoundException) {
            System.out.println("☹ OOPS!!! Task number " + taskNumberInt + " does not exist.");
            return;
        }
    }

    public void executeInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
            case COMMAND_DELETE:
                executeCommandDelete(input.getArguments());
                break;
            default:
                executeInvalidCommand();
            }

            ConsoleInterface.printLineSeparator();
        }
    }
}
