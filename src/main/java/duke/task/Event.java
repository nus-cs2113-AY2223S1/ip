package duke.task;

public class Event extends Task{
    protected String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[E]" +"[" + statusIcon + "] " + this.description +  " (at: " + this.time + ")";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("E | %d | %s | %s", isDoneInNumber, description, time) + System.lineSeparator();
    }
}
