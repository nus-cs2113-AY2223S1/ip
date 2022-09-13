package duke.command;

public class DeleteCommand extends Command {

    private String taskPosition;

    public DeleteCommand() {

    }

    @Override
    public void setArgument(String argument) {
        this.taskPosition = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return  taskPosition;
    }
}
