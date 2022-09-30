package duke.task;

public class Todo extends Task {

    /**
     * Initializes Todo Class
     *
     * @param description refers to todo description
     */
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String SaveAsString() {
        return "[T]" + super.SaveAsString();
    }
}
