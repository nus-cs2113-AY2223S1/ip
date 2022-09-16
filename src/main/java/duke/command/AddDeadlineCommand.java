package duke.command;

import duke.tasks.TaskList;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.addDeadline2(input);

        return new ExecutedCommand(executionMessage);
    }
}
