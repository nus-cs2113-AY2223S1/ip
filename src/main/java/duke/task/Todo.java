package duke.task;

public class Todo extends Task {

    /**
     * Initializes aa Todo class
     * @param description What the todo is about
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Concatenates the task type, isDone and description value of the todo
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Similar to toString
     *
     * @return a String with the task type isDone value enclosed by two square brackets followed by its description
     */
    public String toSaveString() {
        return "[T]" + super.toString();
    }

    /**
     * @return type of the Task
     */
    public String getType(){
        return "T";
    }
}