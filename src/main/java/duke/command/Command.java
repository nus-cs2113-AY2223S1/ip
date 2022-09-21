package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes corresponding command class based on command type
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeIOException Exception when writeToFile has exceptions
     * @throws DukeEmptyDescriptionException Exception when no description for the Task was added
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, DukeIOException;

    /**
     * Returns boolean isExit based on command type
     *
     * @return boolean isExit
     */
    public abstract boolean isExit();
}
