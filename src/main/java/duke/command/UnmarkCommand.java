package duke.command;

public class UnmarkCommand extends Command {

    private String taskPosition;

    public UnmarkCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.taskPosition = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return taskPosition;
    }
}
