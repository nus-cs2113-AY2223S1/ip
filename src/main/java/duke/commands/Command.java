package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This abstract class provides a template for other, specialised {@code Command}s to build upon.
 * It provides information on whether the {@code Command} is an exiting {@code Command},
 * and an abstract method that allows its subclasses to implement the {@code Command}'s execution.
 *
 * @author Dhanish
 */
public abstract class Command {

    private final boolean isExit;

    /**
     * Constructor that instantiates the method based off the input boolean value.
     * Note that this constructor can only be called from this class' subclasses
     *
     * @param isExit - a boolean variable that indicates if this {@code Command} is an exit {@code Command}.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * This abstract method forces each of this class' subclasses to provide an implementation of their specific {@code Command}s.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return true if this method is an {@code ExitCommand}, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
