package main.duke.task;

import main.duke.Utils;
import main.duke.exception.DukeException;

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

    public String dataString() {
        return super.toString() + "/at " + date;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
