package duke.tasks;

/**
 * Represents a todos task which includes a date and time for which it is due.
 */
public class Todos extends Task {

    /**
     * Constructor for a new todos task.
     *
     * @param taskName A name or description given to the task.
     */
    public Todos(String taskName) {
        super(taskName);
        this.type = "T";
    }

    /**
     * Returns a string describing the event.
     *
     * @return A string representing the todos task.
     */
    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ] ";
        return "[T]" + done + this.getTaskName();
    }
}
