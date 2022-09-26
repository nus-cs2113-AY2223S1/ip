package duke.command;

public class FindCommand extends Command {

    private String lookingFor;

    public FindCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.lookingFor = argument;
    }

    @Override
    public String getArgument(boolean b) {
        return lookingFor;
    }
}
