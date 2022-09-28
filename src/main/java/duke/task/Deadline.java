package duke.task;

import duke.Storage;

public class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String date) {
        super(description);
        this.byDate = date;
    }

    @Override
    public String toString() {
        return "[D][" + getIsDoneMarking() + "] " + getDescription() + " (by: " + byDate + ")";
    }

    public String getByDate(){return byDate;}

    @Override
    public String getFileFormat() {
        return "D" +super.getFileFormat() + Storage.divider + byDate;
    }

}
