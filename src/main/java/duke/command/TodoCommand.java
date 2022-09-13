package duke.command;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand() {

    }

    @Override
    public void setArgument(String argument) {
        this.description = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return description;
    }
}
