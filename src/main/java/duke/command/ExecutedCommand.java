package duke.command;

import duke.tasks.TaskList;

public class ExecutedCommand {
    private String executionMessage;
    private TaskList taskList;

    public ExecutedCommand(String executionMessage) {
        this.executionMessage = executionMessage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getExecutionMessage() {
        return executionMessage;
    }
}
