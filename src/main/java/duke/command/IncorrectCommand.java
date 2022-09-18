package duke.command;

public class IncorrectCommand extends Command {
    public IncorrectCommand(String top){
        super(COMMAND_NAME);
        this.top = top;
    }
    public IncorrectCommand(String top, String bottom) {
        this(top);
        this.bottom = bottom;
    }

    public static final String COMMAND_NAME = "incorrect";
    public String top;
    public String bottom = "";

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(this.top, null, this.bottom);
        return result;
    }

}
