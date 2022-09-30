package duke;

/**
 * Represents a todo task
 */
public class ToDo extends Task{

    public ToDo(String name, boolean isDone, char type) {
        super(name, isDone, type);
    }

    @Override
    public String getDateTime() {
        return null;
    }
}
