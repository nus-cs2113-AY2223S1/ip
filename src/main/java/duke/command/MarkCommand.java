package duke.command;

public class MarkCommand extends Command {

    private String taskPosition;

    public MarkCommand() {

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
