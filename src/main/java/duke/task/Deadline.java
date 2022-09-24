package duke.task;

public class Deadline extends Task {

    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    public String toSaveString(){return "[D]" + super.toString() + "/by " + by;}

    public String getType(){
        return "D";
    }
}