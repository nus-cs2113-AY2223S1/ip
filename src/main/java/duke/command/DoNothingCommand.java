package duke.command;

import duke.tasks.TaskList;

public class DoNothingCommand extends Command {
    public DoNothingCommand(TaskList taskList) {
        super(taskList, "");
    }

    @Override
    public ExecutedCommand execute() throws Exception {
        TaskList.doNothing();

        return new ExecutedCommand("", taskList, false);
    }
}
