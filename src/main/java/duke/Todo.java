package duke;

public class Todo extends Task {
    /**
     * Creates duke.Task Object
     *
     * @param description
     */

    public Todo(String description) {
        super(description);
        this.category = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString().substring(3);
    }

    @Override
    public String getSavedString() {
        return "T | " + getStatusIcon() + " | " + description;
    }



}
