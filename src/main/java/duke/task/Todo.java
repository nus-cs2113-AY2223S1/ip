package duke.task;

import java.io.FileNotFoundException;

import duke.exception.DukeException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}