package duke.command;

public class DeadlineCommand extends Command{

    // 0 - description, 1 - time
    private String[] argument = new String[2];

    public DeadlineCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.argument[i] = argument;
    }

    @Override
    public String getArgument(boolean b) {
        if (b) {
            return argument[0];
        }   else {
            return argument[1];
        }
    }
}
