package main.duke.task;

/** Class for specifically creating an Event */
public class Event extends Task {

    private String date;

    /** Constructor for creating an Event given a description and a date */
    public Event(String description, String date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    /** Using the parent class to create the string and data formats for the event */
    @Override
    public String toString() {
        return super.toString() + "(at: " + date + ")";
    }

    public String dataString() {
        return super.toString() + "/at " + date;
    }

}
