package duke.task;

public class Event extends Task {
    private static final String TYPE_EVENT = "[E]";
    public String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = TYPE_EVENT;
    }


    public void printTask() {
        System.out.println(this.taskType + this.getStatusIcon() + " " + this.description + "(" + this.date + ")");
    }
}
