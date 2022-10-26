package tasks;

import static ui.Messages.addMessage;

/**
 * Stores the Deadline class, its attributes and methods.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Prints out a message when the user adds a deadline to the task list
     */
    @Override
    public void taskMessage() {
        addMessage();
        System.out.println("\n" + this);
    }
}
