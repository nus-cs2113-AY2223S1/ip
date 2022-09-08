public class Deadline extends Task {
    private String by;

    public Deadline (String taskDescription, String by){
        super(taskDescription);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    // Prints task
    @Override
    public String showTask(){
        return "[D][" + (isDone ? "\u2713" : " ") + "] " + taskDescription + "(by: " + by + ")";
    }
}
