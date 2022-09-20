package duke.command;

public class IncorrectCommand extends Command {
    public static final String COMMAND_NAME = "incorrect";
    public static final String MESSAGE = "Incorrect Command. Type \"help\" for help.";

    public IncorrectCommand(String message) {
        super(COMMAND_NAME);
        this.message = message;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(message);
    }

}
