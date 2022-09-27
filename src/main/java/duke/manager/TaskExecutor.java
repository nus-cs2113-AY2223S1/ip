package duke.manager;

import duke.exception.MissingArgumentException;
import duke.exception.NoSuchCommandException;
import duke.exception.NoTasksException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.command.Command;

/**
 * This class carries out task execution or creation.
 */
public class TaskExecutor {

    private static final String MESSAGE_INDENTATION = "  ";
    public static final String SPACES_BETWEEN_WORDS = " ";

    /**
     * Prints the list of tasks.
     *
     * @param taskNumber number of tasks in the list
     * @throws NoTasksException If there are no tasks in the list
     */
    private static void listCommand(int taskNumber) throws NoTasksException {
        if (taskNumber == 0) { // Guard Clause
            throw new NoTasksException();
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNumber; i++) {
            Task task = TaskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        // somehow being run after other commands
    }

    /**
     * Calls the <code>listCommand</code> method for printing of the list
     * and handles any exceptions.
     *
     * @param taskNumber number of tasks in the list
     */
    public static void executeList(int taskNumber) {
        try {
            listCommand(taskNumber);
        } catch (NoTasksException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks the task specified by the user as done. The index of the task is in the string
     * <code>argument</code>.
     *
     * @param argument string that contains the task position of task to act on
     * @throws MissingArgumentException If the user did not provide enough arguments
     * @throws NumberFormatException If the user did not provide an integer when they are supposed to
     * @throws IndexOutOfBoundsException If the integer provided is less than 1 and less than the number of tasks
     */
    private static void markCommand(String argument) throws MissingArgumentException, NumberFormatException,
            IndexOutOfBoundsException {
        int taskPosition = CommandParser.returnAsInt(argument);
        Task task = TaskList.get(taskPosition - 1);
        if (task.isDone()) {
            System.out.println("You have already done this task!");
            return;
        }
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    /**
     * Marks the task specified by the user as not done. The index of the task is in the
     * string <code>argument</code>.
     *
     * @param argument string that contains the task position of task to act on
     * @throws MissingArgumentException If the user did not provide enough arguments
     * @throws NumberFormatException If the user did not provide an integer when they are supposed to
     * @throws IndexOutOfBoundsException If the integer provided is less than 1 and less than the number of tasks
     */
    private static void unmarkCommand(String argument) throws MissingArgumentException, NumberFormatException,
            IndexOutOfBoundsException {
        int taskPosition = CommandParser.returnAsInt(argument);
        Task task = TaskList.get(taskPosition - 1);
        if (!task.isDone()) {
            System.out.println("You haven't done this task yet!");
            return;
        }
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    /**
     * Deletes the task specified by the user. The index of the task is in the
     * string <code>argument</code>.
     *
     * @param argument string that contains the task position of task to act on
     * @throws MissingArgumentException If the user did not provide enough arguments
     * @throws NumberFormatException If the user did not provide an integer when they are supposed to
     * @throws IndexOutOfBoundsException If the integer provided is less than 1 and less than the number of tasks
     */
    private static void deleteCommand(String argument) throws MissingArgumentException,
            NumberFormatException, IndexOutOfBoundsException {
        int taskPosition = CommandParser.returnAsInt(argument);
        taskPosition--;
        TaskList.deleteTask(taskPosition);
    }

    /**
     * Calls the relevant methods for execution and handles any exceptions that may occur for commands that
     * come in the form of (type) (task position).
     *
     * @param keyword the type of command
     * @param argument string that contains the task position of task to act on
     */
    private static void executeKeywordAndIntegerCommands(String keyword, String argument) {
        try {
            switch (keyword) {
            case "mark":
                markCommand(argument);
                break;
            case "unmark":
                unmarkCommand(argument);
                break;
            case "delete":
                deleteCommand(argument);
                break;
            default:
                System.out.println("Error executing keyword and integer commands!");
                break;
            }
        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! You did not give me an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You did not give me an integer i that is more than zero and less than" +
                    " the number of tasks");
        }
    }

    /**
     * Creates a <code>Todo</code> task and adds it to the list.
     *
     * @param taskList list of tasks
     * @param description description of Todo task
     * @throws MissingArgumentException If the user did not provide enough arguments
     */
    private static void todoCommand(TaskList taskList, String description) throws MissingArgumentException {
        if (CommandParser.isStringEmpty(description)) {
            throw new MissingArgumentException("description");
        }
        Task task = new Todo(description);
        taskList.addTask(task, true);
    }

    /**
     * Calls the <code>todoCommand</code> for Todo task creation and handles any exceptions.
     *
     * @param taskList list of tasks
     * @param description description of <code>Todo</code> task
     */
    private static void executeTodo(TaskList taskList, String description) {
        try {
            todoCommand(taskList, description);
        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a <code>Deadline</code> task and adds it to the list.
     *
     * @param taskList list of tasks
     * @param description description of <code>Deadline</code> task
     * @param time the due time of <code>Deadline</code> task
     * @throws MissingArgumentException If the user did not provide enough arguments
     */
    private static void deadlineCommand(TaskList taskList, String description, String time) throws
            MissingArgumentException {
        if (CommandParser.isStringEmpty(description)) {
            throw new MissingArgumentException("description");
        }
        if (CommandParser.isStringEmpty(time)) {
            throw new MissingArgumentException("correct time");
        }

        Task task = new Deadline(description, time);
        taskList.addTask(task, true);
    }

    /**
     * Creates a <code>Event</code> task and adds it to the list.
     *
     * @param taskList list of tasks
     * @param description description of <code>Event</code> task
     * @param time the starting time of <code>Event</code> task
     * @throws MissingArgumentException If the user did not provide enough arguments
     */
    private static void eventCommand(TaskList taskList, String description, String time) throws
            MissingArgumentException {
        if (CommandParser.isStringEmpty(description)) {
            throw new MissingArgumentException("description");
        }
        if (CommandParser.isStringEmpty(time)) {
            throw new MissingArgumentException("correct time");
        }
        Task task = new Event(description, time);
        taskList.addTask(task, true);
    }

    /**
     * Calls the <code>deadlineCommand</code> or <code>eventCommand</code> for Deadline and Event task creation
     * and handles any exceptions.
     *
     * @param taskList list of tasks
     * @param description description of the task
     * @param time the time field of the task
     */
    private static void executeDeadlineOrEvent(TaskList taskList, String keyword, String description, String time) {
        try {
            if (keyword.equals("deadline")) {
                deadlineCommand(taskList, description, time);
            } else if (keyword.equals("event")) {
                eventCommand(taskList, description, time);
            }
        } catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds a keyword in the descriptions of tasks stored in the list.
     *
     * @param lookingFor keyword to look for
     * @param taskNumber number of tasks
     * @throws NoTasksException If there are no tasks
     */
    private static void findCommand(String lookingFor, int taskNumber) throws NoTasksException {
        // case-insensitive
        Task task;
        String description;
        String[] splitDescription;
        int tasksFound = 0;
        // no point searching if no tasks
        if (taskNumber == 0) {
            throw new NoTasksException();
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskNumber; i++) {
            task = TaskList.get(i);
            description = task.getDescription();
            splitDescription = description.split(SPACES_BETWEEN_WORDS);
            if (CommandParser.searchArray(splitDescription, lookingFor) != -1) {
                System.out.println((i + 1) + "." + task);
                tasksFound++;
            }
        }
        if (tasksFound == 0) {
            System.out.println("I did not find any matching tasks in your list. ☹");
        }
    }

    /**
     * Calls the method <code>findCommand</code> for finding a keyword in the list.
     *
     * @param lookingFor keyword to look for
     * @param taskNumber number of tasks
     */
    private static void executeFind(String lookingFor, int taskNumber) {
        try {
            findCommand(lookingFor, taskNumber);
        } catch (NoTasksException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls the relevant methods for command execution based on the type of command.
     *
     * @param taskList the list of tasks
     * @param command the command object
     * @throws NoSuchCommandException If the command provided is not recognised by Duke
     */
    public static void execute(TaskList taskList, Command command) throws NoSuchCommandException {
        String keyword = command.getKeyword();
        switch (keyword) {
        case "bye":
            command.setBye(true);
            break;
        case "list":
            executeList(TaskList.getSize());
            break;
        case "mark":
        case "unmark":
        case "delete":
            executeKeywordAndIntegerCommands(keyword, command.getArgument(true));
            break;
        case "todo":
            executeTodo(taskList, command.getArgument(true));
            break;
        case "deadline":
        case "event":
            executeDeadlineOrEvent(taskList, keyword, command.getArgument(true), command.getArgument(false));
            break;
        case "find":
            executeFind(command.getArgument(true), TaskList.getSize());
            break;
        default:
            throw new NoSuchCommandException();
        }
    }
}