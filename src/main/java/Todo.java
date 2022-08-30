public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    public String getCommandIcon(){return "[T]";}
    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + getDescription();
    }
}
