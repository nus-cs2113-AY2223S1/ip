package main.duke.task;

/** Class for specifically creating a Deadline */
public class Deadline extends Task {

    private String dueDate;

    /** Constructor for creating a Deadline given a description and a date */
    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.dueDate = date;
    }

    /** Using the parent class to create the string and data formats for the event */
    @Override
    public String toString() {
        return super.toString() + "(by: " + dueDate + ")";
    }

    public String dataString() {
        return super.toString() + "/by " + dueDate;
    }

}
