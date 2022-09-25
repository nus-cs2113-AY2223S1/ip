package duke.command;

public class DeadlineCommand extends Command{

    public static final int DESCRIPTION = 0;
    public static final int TIME = 1;
    private final String[] argument = new String[2];

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
            return argument[DESCRIPTION];
        }   else {
            return argument[TIME];
        }
    }
}
