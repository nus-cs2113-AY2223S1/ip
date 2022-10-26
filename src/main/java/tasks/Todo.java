package tasks;

import static ui.Messages.addMessage;

/**
 * Stores the Todo class, its attributes and methods.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Prints out a message when the user adds a todo to the task list
     */
    @Override
    public void taskMessage() {
        addMessage();
        System.out.println("\n" + this);
    }
}

