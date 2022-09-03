package duke.error.exceptions.subcommand;

import duke.error.exceptions.DukeException;

/**
 * Exception subclass of {@link DukeException} that facilitates certain exceptions that have both
 * a command and subcommand string.
 */
public abstract class SubCommandException extends DukeException {
    protected final String command;
    protected final String subCommand;

    /**
     * Constructor for exception
     *
     * @param command    command string
     * @param subCommand subcommand string
     */
    public SubCommandException(String command, String subCommand) {
        super();
        this.command = command;
        this.subCommand = subCommand;
    }
}
