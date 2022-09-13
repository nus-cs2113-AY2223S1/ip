package duke.command;

public class MarkCommand extends Command {

    private String taskPosition;

    public MarkCommand() {
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
