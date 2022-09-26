package duke;
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline AddTask(String name , String by){
        Deadline newDeadline = new Deadline(name,by);
        System.out.println(""+newDeadline.toString());
        return newDeadline;
    }

    @Override
    public String recordString() {
        return "D | " + super.recordString() + " | " + by;
    }
    @Override
    public String toString() {
        return this.index +"." + "[D]" + "["+this.getStatusIcon()+"] " + this.name + " (by: " + by + ")";
    }
}
