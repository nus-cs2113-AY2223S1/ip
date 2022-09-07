package main.duke.task;

public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + date + ")";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
