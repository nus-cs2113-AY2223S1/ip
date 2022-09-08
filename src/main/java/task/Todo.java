package task;

public class Todo extends Task{
    protected String by;
    public Todo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + toString());
    }
    @Override
    public String toString() {
        return ("[T][" + super.getStatusIcon() + "] " + super.description);
    }
}
