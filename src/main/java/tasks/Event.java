package tasks;

import static ui.Messages.addMessage;

/**
 * Stores the Event class, its attributes and methods.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Prints out a message when the user adds an event to the task list
     */
    @Override
    public void taskMessage() {
        addMessage();
        System.out.println("\n" + this);
    }
}
