public class Todo extends Task{

    public Todo(String descriptionString) {
        super(descriptionString);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.descriptionString;
    }
}
