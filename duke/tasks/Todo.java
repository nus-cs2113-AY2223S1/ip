package duke.tasks;

public class Todo extends Task {
    protected boolean isDone;
    protected String initial = "T";

    public Todo(String name) {
        super(name);
        this.isDone = false;
    }

    @Override
    /**
     * Returns all the information of the task as a string
     */
    public String toString() {
        return "[" + this.initial + "]" + super.toString();
    }
}
