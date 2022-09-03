package duke.tasks;

import duke.error.exceptions.NoStateChangeException;
import duke.ui.DialogBox;

/**
 * Abstract superclass that list items inherit from. <br><br>
 * <b>Subclasses: </b>
 * <ul><li>{@link duke.tasks.tasktypes.ToDoTask}</li> <li>{@link duke.tasks.tasktypes.DeadlineTask}</li>
 * <li>{@link duke.tasks.tasktypes.EventTask}</li></ul>
 */
public abstract class Task {
    /** Text that each item contains */
    private final String TEXT;

    /** Field that tracks if task is marked as done or not */
    private boolean isDone;

    /**
     * Constructor that initializes {@link Task#TEXT} and
     * {@link Task#isDone} field.
     *
     * @param text text of the item
     */
    public Task(String text) {
        TEXT = text.trim();
        isDone = false;
    }

    /**
     * Parses Task item as string. <br>Example: <code>[ ][T] go grocery shopping</code>
     *
     * @return item expressed as a string
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + getTypeIcon() + "] " + TEXT
                + DialogBox.rightAlign(getPostFix());
    }

    /**
     * Throws an exception if isDone does not change (already marked/unmarked)
     *
     * @param isDone state to change {@link Task#isDone} to.
     * @throws NoStateChangeException If task is already the same state as the desired change
     */
    public void setDone(boolean isDone) throws NoStateChangeException {
        if (this.isDone == isDone) {
            throw new NoStateChangeException(isDone);
        }
        this.isDone = isDone;
    }

    /**
     * Returns text of task.
     *
     * @return text of task expressed as a String
     */
    public String getText() {
        return TEXT;
    }

    /**
     * Returns the type icon based on item type.
     *
     * @return the type icon that represents the item type
     */
    protected abstract String getTypeIcon();

    /**
     * Overridden in the case of certain methods that attach
     * other comments to the back of tasks.
     *
     * @return string to be postfixed at the end
     */
    protected String getPostFix() {
        return "";
    }

    /**
     * Status icon that changes when item is marked done or not done.
     *
     * @return String of status icon
     */
    private String getStatusIcon() {
        return isDone ? "■" : " ";
    }
}
