package duke;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString(){
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
