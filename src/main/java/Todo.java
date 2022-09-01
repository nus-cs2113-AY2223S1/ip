public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getPrintString() {
        return ("[T]" + super.getPrintString());
    }
}
