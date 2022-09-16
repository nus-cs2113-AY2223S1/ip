package duke.command;

import duke.tasks.TaskList;

public class ExecutedCommand {
    private String executionMessage;
    private TaskList taskList;
    private boolean isTaskListChanged;

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
