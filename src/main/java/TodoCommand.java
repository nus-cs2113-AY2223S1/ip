public class TodoCommand extends Command implements FormatChecker {
    private final String description;
    private final static CommandType commandType = CommandType.TASK;

    /**
     * Construct an event given an almost unprocessed input command
     *
     * @param description description of todo command
     * @throws WrongCommandFormatException when input command is not in correct format
     */
    public TodoCommand(String description) throws WrongCommandFormatException {
        this.description = description;
        FormatChecker.checkNullString(description);
    }

    /**
     * get the task that is specified in current todo command
     *
     * @return the task specified in the todo command
     */
    @Override
    public Task getTask() {
        return new Todo(description, false);
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
