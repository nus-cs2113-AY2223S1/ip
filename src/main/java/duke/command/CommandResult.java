package duke.command;

import java.util.List;
import java.util.Optional;

import duke.data.task.Task;

/**
 * Represent the result of the command
 */
public class CommandResult {

    public List<? extends Task> target;
    public String message;

    /**
     * Initialization without target
     */
    public CommandResult(String message) {
        this.message = message;
    }

    /**
     * Initialization with target
     */
    public CommandResult(String message, List<? extends Task> target) {
        this.message = message;
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public Optional<List<? extends Task>> getTarget() {
        return Optional.ofNullable(target);
    }

}
