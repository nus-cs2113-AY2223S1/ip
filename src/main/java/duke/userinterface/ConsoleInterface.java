package duke.userinterface;

import duke.commands.ConsoleCommand;
import duke.commands.ConsoleCommandBye;
import duke.commands.ConsoleCommandDeadline;
import duke.commands.ConsoleCommandDelete;
import duke.commands.ConsoleCommandEvent;
import duke.commands.ConsoleCommandFind;
import duke.commands.ConsoleCommandList;
import duke.commands.ConsoleCommandMark;
import duke.commands.ConsoleCommandTodo;
import duke.commands.ConsoleCommandUnmark;
import duke.common.Configurations;
import duke.common.Messages;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.TaskManager;
import duke.exceptions.ConsoleInputParserException;
import duke.exceptions.TaskManagerException;
import duke.data.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides functions to interface with user via standard input and standard output.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleInterface {
    private Scanner scanner;
    private TaskManager taskManager;

    /**
     * Initializes console interface.
     */
    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        taskManager = new TaskManager(Configurations.TASKS_DIRECTORY_PATH, Configurations.TASKS_FILENAME);
    }

    /**
     * Prints logo to standard out.
     */
    @SuppressWarnings("TextBlockMigration")
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a blank line to standard out.
     */
    public static void printBlankLine() {
        System.out.println();
    }

    /**
     * Prints greeting message to standard out.
     */
    public static void printGreetingMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GREETING);
    }

    /**
     * Prints goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GOODBYE);
    }

    /**
     * Prints error message to standard out.
     */
    public static void printErrorMessage(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
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

        return new ConsoleInput(command, argument);
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
     * @param consoleCommandMark Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandMark(ConsoleCommandMark consoleCommandMark) {
        int taskNumber = consoleCommandMark.getTaskNumber();

        try {
            taskManager.markTaskAsCompleted(taskNumber);

            System.out.println("Nice! I've marked this task as done:");
            taskManager.getTask(taskNumber).print();

            taskManager.saveTasks();
        } catch (TaskManagerException.TaskNotFoundException taskNotFoundException) {
            printErrorMessage("The task " + taskNumber + " does not exist.");
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param consoleCommandUnmark Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandUnmark(ConsoleCommandUnmark consoleCommandUnmark) {
        int taskNumber = consoleCommandUnmark.getTaskNumber();

        try {
            taskManager.markTaskAsUncompleted(taskNumber);

            System.out.println("OK, I've marked this task as not done yet:");
            taskManager.getTask(taskNumber).print();

            taskManager.saveTasks();
        } catch (TaskManagerException.TaskNotFoundException e) {
            printErrorMessage("The task " + taskNumber + " does not exist.");
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a todo into task manager.
     *
     * @param consoleCommandTodo Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandTodo(ConsoleCommandTodo consoleCommandTodo) {
        String description = consoleCommandTodo.getDescription();

        Todo todo = new Todo(description);
        taskManager.addTask(todo);

        System.out.println("Got it. I've added this task:");
        todo.print();
        System.out.println("Now you have " + taskManager.getNumTasks() + " tasks in the list.");

        try {
            taskManager.saveTasks();
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a deadline into task manager.
     *
     * @param consoleCommandDeadline Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandDeadline(ConsoleCommandDeadline consoleCommandDeadline) {
        String description = consoleCommandDeadline.getDescription();
        LocalDateTime by = consoleCommandDeadline.getBy();

        Deadline deadline = new Deadline(description, by);
        taskManager.addTask(deadline);

        System.out.println("Got it. I've added this task:");
        deadline.print();
        System.out.println("Now you have " + taskManager.getNumTasks() + " tasks in the list.");

        try {
            taskManager.saveTasks();
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds an event into task manager.
     *
     * @param consoleCommandEvent Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandEvent(ConsoleCommandEvent consoleCommandEvent) {
        String description = consoleCommandEvent.getDescription();
        LocalDateTime startAt = consoleCommandEvent.getStartAt();
        LocalDateTime endAt = consoleCommandEvent.getEndAt();

        Event event = new Event(description, startAt, endAt);
        taskManager.addTask(event);

        System.out.println("Got it. I've added this task:");
        event.print();
        System.out.println("Now you have " + taskManager.getNumTasks() + " tasks in the list.");

        try {
            taskManager.saveTasks();
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Deletes a task from task manager.
     *
     * @param consoleCommandDelete Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandDelete(ConsoleCommandDelete consoleCommandDelete) {
        int taskNumber = consoleCommandDelete.getTaskNumber();

        try {
            Task task = taskManager.deleteTask(taskNumber);

            System.out.println("Noted. I've removed this task:");
            task.print();
            System.out.println("Now you have " + taskManager.getNumTasks() + " tasks in the list.");

            taskManager.saveTasks();
        } catch (TaskManagerException.TaskNotFoundException e) {
            printErrorMessage("The task " + taskNumber + " does not exist.");
        } catch (TaskManagerException.TasksFileIOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Find tasks in task manager that matches description.
     *
     * @param consoleCommandFind Command parsed by the function {@link ConsoleInputParser#parseConsoleInput(ConsoleInput)}.
     */
    public void executeCommandFind(ConsoleCommandFind consoleCommandFind) {
        String description = consoleCommandFind.getDescription();

        ArrayList<Task> matchingTasks = taskManager.findTask(description);

        System.out.println("Here are the matching tasks in your list:");
        for (Task task : matchingTasks) {
            System.out.print(taskManager.getTaskNumber(task) + ".");
            task.print();
        }
    }

    /**
     * Executes command line interface which interacts with user.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public void executeProgram() {
        ConsoleInterface.printBlankLine();

        while (true) {
            ConsoleInput consoleInput = getConsoleInput();

            ConsoleInterface.printBlankLine();

            ConsoleCommand consoleCommand = null;
            boolean hasParseError = true;

            try {
                consoleCommand = ConsoleInputParser.parseConsoleInput(consoleInput);
                hasParseError = false;
            } catch (ConsoleInputParserException.InvalidCommandMarkException |
                     ConsoleInputParserException.InvalidCommandUnmarkException |
                     ConsoleInputParserException.InvalidCommandTodoException |
                     ConsoleInputParserException.InvalidCommandDeadlineException |
                     ConsoleInputParserException.InvalidCommandEventException |
                     ConsoleInputParserException.InvalidCommandDeleteException |
                     ConsoleInputParserException.InvalidCommandFindException e) {
                printErrorMessage(e.getMessage());
            } catch (ConsoleInputParserException.CommandNotFoundException e) {
                printErrorMessage("I'm sorry, but I don't know what that means :-(");
            }

            if (hasParseError) {
            } else if (consoleCommand instanceof ConsoleCommandBye) {
                return;
            } else if (consoleCommand instanceof ConsoleCommandList) {
                executeCommandList();
            } else if (consoleCommand instanceof ConsoleCommandMark) {
                executeCommandMark((ConsoleCommandMark) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandUnmark) {
                executeCommandUnmark((ConsoleCommandUnmark) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandTodo) {
                executeCommandTodo((ConsoleCommandTodo) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDeadline) {
                executeCommandDeadline((ConsoleCommandDeadline) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandEvent) {
                executeCommandEvent((ConsoleCommandEvent) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDelete) {
                executeCommandDelete((ConsoleCommandDelete) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandFind) {
                executeCommandFind((ConsoleCommandFind) consoleCommand);
            } else {
            }

            ConsoleInterface.printBlankLine();
        }
    }
}
