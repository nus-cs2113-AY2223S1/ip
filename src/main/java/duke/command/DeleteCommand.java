package duke.command;

public class DeleteCommand extends Command {

    private String taskPosition;

    public DeleteCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.taskPosition = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return  taskPosition;
    }
}
