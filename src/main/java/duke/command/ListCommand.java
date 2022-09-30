package duke.command;

/**
 * <code>ListCommand</code> is a class for list commands.
 * It indicates a desire to print out all tasks stored in the list.
 */
public class ListCommand extends Command {

    /**
     * Constructor of <code>ListCommand</code>.
     */
    public ListCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int index) {
    }

    @Override
    public String getArgument(boolean isFirstIndex) {
        return "";
    }
}
