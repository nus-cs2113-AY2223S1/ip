package duke.parser;

import static duke.common.Constants.BYE_MESSAGE;

import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Parser class takes user input from the UI and interprets it to run operations on the TaskList and Storage
 * objects, and provides output to the UI. Much of the logic for the commands is interpreted here.
 */

public class Parser {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Creates a new Parser object.
     *
     * @param ui An instance of {@link Ui}
     * @param storage An instance of {@link Storage}
     * @param tasks An instance of {@link TaskList}
     */
    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Interprets and runs a command provided by the user. This is the main entry point for Parser objects; all user
     * commands will first go through this function, which will then interpret the command and execute other functions
     * based on the input.
     *
     * @param command Command provided by the user.
     */
    public void runCommand(String command) {
        String[] args = command.split(" ", 2);
        String action = args[0];
        switch (action) {
        case "bye":
            ui.printLine(BYE_MESSAGE);
            System.exit(0);
            // no break needed, the code has already exited
        case "list":
            listTasks();
            break;
        case "mark":
            // fallthrough
        case "unmark":
            boolean isDone = action.equals("mark");
            if (args.length < 2) {
                ui.printLine("Not enough arguments, dude.");
                break;
            }
            int index;
            try {
                index = Integer.parseInt(args[1].trim()) - 1;
                setTaskStatus(index, isDone);
            } catch (NumberFormatException exception) {
                // user either didn't enter a number or number was too big for integer
                // not a nice way to use exceptions but alternatives are cumbersome
                ui.printLine("That's not even a number, dude.");
            }
            break;
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            if (args.length < 2) {
                ui.printLine("Not enough arguments, dude.");
                break;
            }
            String taskData = args[1];
            addTask(action, taskData);
            break;
        case "find":
            if (args.length < 2) {
                ui.printLine("Not enough arguments, dude.");
                break;
            }
            String searchTerm = args[1];
            findAndShowTasks(searchTerm);
            break;
        case "delete":
            try {
                index = Integer.parseInt(args[1].trim()) - 1;
                deleteTask(index);
            } catch (NumberFormatException exception) {
                ui.printLine("That's not even a number, dude.");
            }
            break;
        default:
            ui.printLine("I don't recognise that command, dude.");
            break;
        }
    }

    private void findAndShowTasks(String searchTerm) {
        ui.printLine(tasks.findTasks(searchTerm));
    }

    /**
     * Creates a task depending on user input and adds it to the task list. This function should only be called by
     * {@link Parser#runCommand(String)}.
     *
     * @param taskType The type of task the user wants to add; the possible values of this function are determined by
     *                 {@link Parser#runCommand(String)}
     * @param taskData The data provided by the user after the command; this includes the task description and date/time,
     *                 if any.
     */
    private void addTask(String taskType, String taskData) {
        Task task;
        try {
            switch (taskType) {
            case "todo":
                task = createTodo(taskData);
                break;
            case "deadline":
                task = createDeadline(taskData);
                break;
            case "event":
                task = createEvent(taskData);
                break;
            default:
                ui.printLine("How did we get here?"); // this should have been validated by runCommand()
                return; // instead of break, we want to stop function execution
            }
        } catch (InvalidArgumentException exception) {
            ui.printLine(exception.getMessage());
            return;
        }
        tasks.addTask(task);
        ui.printLine("OK, dude, I've added this task: ");
        ui.printLine(task);
        ui.printLine("You have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

    private Todo createTodo(String taskData) {
        return new Todo(taskData);
    }

    private Task createEvent(String taskData) throws InvalidArgumentException {
        if (!taskData.contains("/at")) {
            throw new InvalidArgumentException("Missing /at parameter");
        }
        String[] inputArr = taskData.split("/at");
        String description = inputArr[0].trim();
        String at = inputArr[1].trim();
        return new Event(description, at);
    }


    private Deadline createDeadline(String taskData) throws InvalidArgumentException {
        if (!taskData.contains("/by")) {
            throw new InvalidArgumentException("Missing /by parameter");
        }
        String[] inputArr = taskData.split("/by");
        String description = inputArr[0].trim();
        String by = inputArr[1].trim();
        return new Deadline(description, by);
    }

    private void setTaskStatus(int taskIndex, boolean isDone) {
        if (!isValidIndex(taskIndex)) {
            ui.printLine("There's no task with that number, dude.");
            return;
        }

        Task task = tasks.getTask(taskIndex);
        task.setDone(isDone);
        ui.printLine("Okay, dude, I've marked this task as " + (isDone ? "done" : "not done yet") + ": ");
        ui.printLine(task.getDescription());
        storage.save(tasks);
    }

    /**
     * Verifies whether an index points to a valid entry in the {@link TaskList}. Returns true if the index is valid
     * and false otherwise.
     *
     * @param taskIndex Index of a task in a {@link TaskList}.
     * @return Whether the index points to a valid task in the {@link TaskList}.
     */
    private boolean isValidIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    private void listTasks() {
        ui.printLine(tasks);
    }

    private void deleteTask(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            ui.printLine("There's no task with that number, dude.");
            return;
        }

        Task task = tasks.getTask(taskIndex);
        ui.printLine("Deleting task: " + task.getDescription());
        tasks.delTask(taskIndex);
        storage.save(tasks);
    }
}
