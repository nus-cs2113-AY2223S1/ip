package duke.command;

import duke.tasks.TaskList;

public class AddEventCommand extends Command {
    public AddEventCommand(TaskList taskList, String input) {
        super(taskList, input);
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        String executionMessage = TaskList.addEvent(input);

        return new ExecutedCommand(executionMessage, taskList, true);
    }

}
