public class Todo extends Task {
    protected boolean isDone;

    public Todo(String name) {
        super(name);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
