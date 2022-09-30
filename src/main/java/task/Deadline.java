package task;

import task.Task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("  " + this);
    }

    public Deadline(String description, String by, boolean status) {
        super(description, status);
        this.by = by;
        System.out.println("  " + this);
    }

    public String getBy(){
        return by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}