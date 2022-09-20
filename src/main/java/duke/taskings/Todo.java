package duke.taskings;

public class Todo extends Task {


    public Todo(String description, boolean isDone ) {
        super(description,isDone);
        this.taskType = "T";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
