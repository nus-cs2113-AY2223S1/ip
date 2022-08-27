package tasks;

import UI.DialogBox;

/**
 * Abstract superclass that list items inherit from. <br>
 * <b>Subclasses: </b>
 * <ul><li>{@link tasks.tasktypes.ToDoTask}</li> <li>{@link tasks.tasktypes.DeadlineTask}</li>
 * <li>{@link tasks.tasktypes.EventTask}</li></ul>
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

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getText() {
        return TEXT;
    }

    /**
     * Should always be overridden. Returns the type icon based on item type.
     *
     * @return the type icon that represents the item type
     */
    protected String getTypeIcon() {
        return "???";
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

    private String getStatusIcon() {
        return isDone ? "■" : " ";
    }
}
