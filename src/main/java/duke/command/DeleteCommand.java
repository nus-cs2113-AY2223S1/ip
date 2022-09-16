package duke.command;

import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.deleteTask(input);

        return new ExecutedCommand(executionMessage, taskList, true);
    }
}
