package duke.command;

import duke.tasks.TaskList;

public class MarkAsDoneCommand extends Command {
    public MarkAsDoneCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.markAsDone(input);
        return new ExecutedCommand(executionMessage, taskList, true);
    }
}
