public class DeleteCommand extends Command implements FormatChecker {
    private final int index;
    private final static CommandType commandType = CommandType.DELETE;

    /**
     * Construct a delete command including task to delete
     *
     * @param index the index of task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * get the index of task to delete
     *
     * @return the index of task to delete
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     * get the type of current delete command
     *
     * @return command type of delete command, DELETE
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}

