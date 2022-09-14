package duke.command;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.description = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return description;
    }
}
