package duke.task;

import duke.DukeException;

public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String name, String deadline) throws DukeException {
        super(name);
        if ("".equals(name)) {
            throw new DukeException("Deadline name cannot be empty");
        }
        if (deadline == null) {
            throw new DukeException("Please provide a deadline (/by)");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
