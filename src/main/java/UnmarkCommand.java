public class UnmarkCommand extends Command {
    private final int index; //true for mark, false for unmark
    private final static CommandType commandType = CommandType.UNMARK;

    /**
     * Construct an unmark command that unmark a task
     *
     * @param index the index of task
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * get index of the task to unmark
     *
     * @return the index of task
     */
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
