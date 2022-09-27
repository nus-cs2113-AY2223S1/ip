package duke.task;

public class Todo extends Task {

    /**
     * Initializes aa Todo class
     * @param description What the todo is about
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSaveString() {
        return "[T]" + super.toString();
    }

    public String getType(){
        return "T";
    }
}