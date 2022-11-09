package duke.task;
public class Todo extends Task{
    public String getTaskIcon() {
        return "T";
    }

    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the description of the task
     * @return the description of task
     */
    public String getDescription() {
        int firstDivider = description.indexOf("todo");
        String descTask = description.substring(firstDivider);
        return descTask;
    }
}
