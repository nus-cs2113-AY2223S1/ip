package duke.command;

import duke.tasks.TaskList;

/**
 * Abstract of a command that contains
 * TaskList <code>taskList</code> represents the taskList the command is executed on
 * String <code>input</code> represents the arguments for the command to be executed
 */
public abstract class Command {
    protected TaskList taskList;
    protected String input;

    /** Constructor */
    protected Command(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes command based on the given commandArguments on taskList
     *
     * @throws Exception when commandArguments do not fit the required format
     */
    public abstract ExecutedCommand execute() throws Exception;
}
