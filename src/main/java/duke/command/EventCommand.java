package duke.command;

/**
 * <code>EventCommand</code> is a class for event commands.
 * It stores parsed parts of user input necessary for creation of Event object, namely
 * the description of task and the starting time of an event in an array.
 */
public class EventCommand extends Command {

    public static final int DESCRIPTION_INT = 0;
    public static final int TIME_INT = 1;
    private final String[] argument = new String[2];

    /**
     * Constructor of <code>EventCommand</code>.
     */
    public EventCommand() {
        super();
    }

    /**
     * Method to store the parameters of a command into the <code>argument</code> array, the
     * index array to store in is indicated by <code>index</code>. 0 for description and 1 for
     * starting time.
     *
     * @param argument either description or starting time based on <code>index</code>
     * @param index integer that dictates which index of an array parameters should be stored.
     */
    @Override
    public void setArgument(String argument, int index) {
        this.argument[index] = argument;
    }

    /**
     * Get parts of user input necessary for command execution.
     * If <code>type</code> is true, return the description of the event command. If false,
     * return the starting time of the event command.
     *
     * @param type boolean value that dictates which element of an array containing the parameters should
     *          be returned.
     * @return either the description or starting time of event depending on <code>type</code>
     */
    @Override
    public String getArgument(boolean type) {
        if (type) {
            return argument[DESCRIPTION_INT];
        }   else {
            return argument[TIME_INT];
        }
    }
}
