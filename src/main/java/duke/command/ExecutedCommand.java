package duke.command;

import duke.tasks.TaskList;

/**
 * Parent class that serves as base for executing other commands and constructing acknowledgement
 * messages to display to user upon execution of command
 */
public class ExecutedCommand {
    private final String executionMessage;
    private final TaskList taskList;
    private final boolean isTaskListChanged;

    /**
     * Constructor
     */
    public ExecutedCommand(String executionMessage, TaskList taskList, boolean isTaskListChanged) {
        this.executionMessage = executionMessage;
        this.taskList = taskList;
        this.isTaskListChanged = isTaskListChanged;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public boolean isTaskListChanged(){
        return isTaskListChanged;
    }

    public String getExecutionMessage() {
        return executionMessage;
    }
}
