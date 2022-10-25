package duke.task;

public class Todo extends Task {

    /**
     * Constructor of Task based on description
     *
     * @param description Description of Task
     */
    public Todo(String description) {
        super(description);
        this.category = "T";
    }

    /**
     * Printed output of Todo Task
     *
     * @return formatted printed message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString().substring(3);
    }

    /**
     * Output of Todo Task when saved to Storage File
     *
     * @return formatted message
     */
    @Override
    public String getSavedString() {
        return "T | " + getStatusIcon() + " | " + description;
    }



}
