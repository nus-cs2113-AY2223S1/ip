package duke.task;

import duke.FileManager;

public class Event extends Task {
    private String atDate;


    public Event(String description, String date) {
        super(description);
        this.atDate = date;
    }

    @Override
    public String toString() {
        return "[E][" + getIsDoneMarking() + "] " + getDescription() +
                " (at: " + atDate + ")";
    }

    public String getAtDate() {
        return atDate;
    }
    @Override
    public String getFileFormat() {
        return "E" +super.getFileFormat() + FileManager.divider + atDate;
    }

}
