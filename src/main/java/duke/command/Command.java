package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, DukeIOException;

    public abstract boolean isExit();
}
