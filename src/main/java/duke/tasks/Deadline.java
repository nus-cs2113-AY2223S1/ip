package duke.tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }
    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    // Prints task
    @Override
    public String showTask() {
        return "[D][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription + "(by: " + by + ")";
    }
    @Override
    public String writeTaskToFile(){
        return "D | " + (isDone ? 1 : 0) + " | " + taskDescription;
    }

}
