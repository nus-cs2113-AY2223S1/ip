package duke.command;

/**
 * <code>ByeCommand</code> is a class for bye commands.
 */
public class ByeCommand extends Command {

    /**
     * Constructor of <code>ByeCommand</code>.
     */
    public ByeCommand() {
        super();
    }

    @Override
    public void setArgument(String argument, int index) {
    }

    @Override
    public String getArgument(boolean type) {
        return "";
    }
}