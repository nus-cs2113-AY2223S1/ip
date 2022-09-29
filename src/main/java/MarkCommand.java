public class MarkCommand extends Command {
    private final int index; //true for mark, false for unmark
    private final static CommandType commandType = CommandType.MARK;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * get the index of the task to mark as done
     *
     * @return the index of the task
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * get the type of current mark command
     *
     * @return command type of mark command, MARK
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
