package duke.command;

import duke.tasks.TaskList;

/** A null command to be executed when the command is not recognised */
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
