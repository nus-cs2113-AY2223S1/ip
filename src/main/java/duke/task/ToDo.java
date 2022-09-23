package duke.task;

/**
 * Represents a todo.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the information of the todo for printing.
     *
     * @return Todo in string format.
     */

    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[T]" +"[" + statusIcon + "] " + this.description;
    }

    /**
     * Sets the complete status of the todo.
     *
     * @param isDone Complete status to be updated to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the information of the todo for storing.
     *
     * @return Todo in string format.
     */
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("T | %d | %s", isDoneInNumber, description) + System.lineSeparator();
    }
}

