package duke.command;

import duke.tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.getTaskList();
        return new ExecutedCommand(executionMessage, taskList, false);
    }
}
