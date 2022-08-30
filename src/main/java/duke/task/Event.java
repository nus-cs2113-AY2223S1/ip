package duke.task;
public class Event extends Task {
    public String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[E]";
    }


    public void printTask() {
        System.out.println(this.taskType + this.getStatusIcon() + " " + this.description + "(" + this.date + ")");
    }
}
