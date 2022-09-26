package duke.command;

public class EventCommand extends Command {

    public static final int DESCRIPTION_INT = 0;
    public static final int TIME_INT = 1;
    private final String[] argument = new String[2];

    public EventCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int i) {
        this.argument[i] = argument;
    }

    @Override
    public String getArgument(boolean type) {
        if (type) {
            return argument[DESCRIPTION_INT];
        }   else {
            return argument[TIME_INT];
        }
    }
}
