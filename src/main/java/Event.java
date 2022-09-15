public class Event extends Task{
    private String at;

    public Event(String taskDescription, String at){
        super(taskDescription);
        this.at =at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String showTask() {
        return "[E][" + (isDone ? "\u2713" : " ") + "] " + taskDescription + "(at: " + at + ")";
    }
}