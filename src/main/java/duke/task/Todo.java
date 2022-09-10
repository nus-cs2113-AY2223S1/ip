package duke.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toOutputFileFormat() {
        String out = "todo " + description;
        if (this.isDone) {
            return (out + " X");
        } else {
            return (out + " O");
        }
    }
}
