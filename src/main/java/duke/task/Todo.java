package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[O]" + str;
    }
}
