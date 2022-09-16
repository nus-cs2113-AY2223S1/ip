package duke.task;

import duke.DukeException;

public class TodoTask extends Task {
    public TodoTask(String name) throws DukeException {
        this(name, false);
    }

    public TodoTask(String name, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException("Todo name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return String.format("todo %s /done %s", getName(), isDone());
    }
}
