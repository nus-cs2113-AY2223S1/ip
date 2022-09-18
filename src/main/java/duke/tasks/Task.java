package duke.tasks;

import duke.error.exceptions.NoStateChangeException;
import duke.ui.DialogBox;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract superclass that items in {@link TaskList} inherit from. <br><br>
 * <b>Subclasses: </b>
 * <ul><li>{@link duke.tasks.tasktypes.ToDoTask}</li> <li>{@link duke.tasks.tasktypes.DeadlineTask}</li>
 * <li>{@link duke.tasks.tasktypes.EventTask}</li></ul>
 */
public abstract class Task {
    /**
     * Status icon
     */
    private static final String STATUS_ICON = "■";
    /**
     * Text that each item contains
     */
    private final String TEXT;
    /**
     * Field that tracks if task is marked as done or not
     */
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
     * Getter for the {@link Task#STATUS_ICON} icon that denotes if a task is done or not
     *
     * @return String
     */
    public static String getStatusIcon() {
        return STATUS_ICON;
    }

    /**
     * Parses Task item as string. <br>Example: <code>[ ][T] go grocery shopping</code>
     *
     * @return item expressed as a string
     */
    public String toString() {
        return "[" + getStatusIconConditional() + "]" + "[" + getTypeIcon() + "] " + TEXT
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
     * Overridden in the case of certain methods that attach
     * other comments to the back of tasks.
     *
     * @return string to be postfixed at the end
     */
    protected String getPostFix() {
        return "";
    }

    /**
     * Abstract method for the type icon of each subclass.
     */
    protected abstract String getTypeIcon();

    /**
     * Status icon that changes when item is marked done or not done.
     *
     * @return String of status icon
     */
    protected String getStatusIconConditional() {
        return isDone ? STATUS_ICON : " ";
    }

    /**
     * Status icon for saving (because ■ doesn't play nice with reading/writing)
     *
     * @return String of status icon
     */
    protected String getStatusIconSave() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns {@link List} with relevant details for saving.
     *
     * @return List of strings containing details of {@link Task} instance.
     */
    public List<String> getSaveItems() {
        return Arrays.asList(getTypeIcon(), getStatusIconSave(), getText());
    }
}
