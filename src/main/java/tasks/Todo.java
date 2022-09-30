package tasks;

public class Todo extends Task{

    public Todo(String descriptionString) {
        super(descriptionString);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.descriptionString;
    }

    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "T|" + marked + "|" + super.descriptionString;
    }
}
