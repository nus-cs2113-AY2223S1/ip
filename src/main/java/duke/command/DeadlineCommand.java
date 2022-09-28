package duke.command;

/**
 * <code>DeadlineCommand</code> is a class for deadline commands.
 * It stores parsed parts of user input necessary for creation of Deadline object, namely
 * the description of task and the due time of a deadline in an array.
 */
public class DeadlineCommand extends Command{

    public static final int DESCRIPTION_INT = 0;
    public static final int TIME_INT = 1;
    private final String[] argument = new String[2];

    /**
     * Constructor of <code>DeadlineCommand</code>.
     */
    public DeadlineCommand() {
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
     * If <code>type</code> is true, return the description of the deadline command. If false,
     * return the due time of the deadline command.
     *
     * @param isFirstIndex boolean value that dictates which element of an array containing the parameters should
     *          be returned.
     * @return either the description or due time of deadline depending on <code>type</code>
     */
    @Override
    public String getArgument(boolean isFirstIndex) {
        if (isFirstIndex) {
            return argument[DESCRIPTION_INT];
        }   else {
            return argument[TIME_INT];
        }
    }
}
