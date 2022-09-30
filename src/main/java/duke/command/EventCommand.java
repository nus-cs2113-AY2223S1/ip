package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidEventInputException;
import duke.task.TaskList;

public class EventCommand extends Command {
    public EventCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "event";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws InvalidEventInputException {
        TaskList.addEvent(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
    