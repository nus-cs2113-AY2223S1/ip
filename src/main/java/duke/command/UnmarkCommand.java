package duke.command;

public class UnmarkCommand extends Command {

    private String taskPosition;

    public UnmarkCommand() {

    }

    @Override
    public void setArgument(String argument) {
        this.taskPosition = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return taskPosition;
    }
}
