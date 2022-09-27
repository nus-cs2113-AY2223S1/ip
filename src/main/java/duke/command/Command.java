package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

/**
 * Represents a command inputted by the user with all the parameters extracted from the string of command.
 * The execution of command is done in this class object.
 */
public class Command {
    private final String command;
    private int taskNumber;
    private String description;
    private String datetime;
    private boolean isExit;

    /**
     * Constructor of <code>Command</code> class when an invalid command is inputted.
     */
    public Command() {
        this.command = CommandMenu.INVALID_COMMAND;
        this.isExit = false;
    }

    /**
     * Constructor of <code>Command</code> class for commands that don't need any parameters.
     * For example, help, list and bye command.
     * @param command Command keyword of the command inputted.
     */
    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    /**
     * Constructor of <code>Command</code> class for commands that need a task number to be provided.
     * For example, mark task as done, mark task as undone and delete task operations.
     * @param command Command keyword of the command inputted.
     * @param taskNumber Task number of the task to be processed on.
     */
    public Command(String command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    /**
     * Constructor of <code>Command</code> class for commands that only need a description to be provided.
     * For example, adding a todo task and finding matching tasks.
     * @param command Command keyword of the command inputted.
     * @param description Description of the task to be added.
     */
    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        this.isExit = false;
    }

    /**
     * Constructor of <code>Command</code> class for commands that need a description and a datetime to be provided.
     * For example, adding an event or a deadline task.
     * @param command Command keyword of the command inputted.
     * @param description Description of the task to be added.
     * @param datetime Date and time of the task to be added.
     */
    public Command(String command, String description, String datetime) {
        this.command = command;
        this.description = description;
        this.datetime = datetime;
        this.isExit = false;
    }

    /**
     * Execute the command. It executes task list operations to the specified task list, and interact with users using
     * the specified user interface.
     * @param taskList List of <code>Task</code> objects to be processed on.
     * @param ui User interface of the application.
     */
    public void execute(TaskList taskList, Ui ui) {
        switch(command) {
        case CommandMenu.HELP_COMMAND:
            CommandMenu.display(ui);
            break;
        case CommandMenu.LIST_COMMAND:
            taskList.list(ui);
            break;
        case CommandMenu.EXIT_COMMAND:
            this.isExit = true;
            break;
        case CommandMenu.MARK_COMMAND:
            taskList.markTaskAsDone(taskNumber, ui);
            break;
        case CommandMenu.UNMARK_COMMAND:
            taskList.markTaskAsUndone(taskNumber, ui);
            break;
        case CommandMenu.DELETE_COMMAND:
            taskList.delete(taskNumber, ui);
            break;
        case CommandMenu.ADD_TODO_COMMAND:
            Todo todo = new Todo(description);
            taskList.add(todo, ui);
            break;
        case CommandMenu.ADD_DEADLINE_COMMAND:
            Deadline deadline = new Deadline(description, datetime);
            taskList.add(deadline, ui);
            break;
        case CommandMenu.ADD_EVENT_COMMAND:
            Event event = new Event(description, datetime);
            taskList.add(event, ui);
            break;
        case CommandMenu.FIND_COMMAND:
            TaskList matchingTasks = taskList.find(description, ui);
            matchingTasks.list(ui);
            break;
        case "":
            ui.showEmptyInputErrorMessage();
            break;
        case CommandMenu.INVALID_COMMAND:
            ui.showInvalidCommandErrorMessage();
            break;
        }
    }

    /**
     * Return the boolean value of whether the command has exit the application.
     * @return Boolean value of whether the application is exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
