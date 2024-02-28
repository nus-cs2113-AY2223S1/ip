public class DeadlineCommand extends Command implements FormatChecker {

    private final String description;
    private final String time;
    private final static CommandType commandType = CommandType.TASK;

    /**
     * Construct a deadline given an almost unprocessed input command
     *
     * @param complexString a complex string that contains both deadline description and time
     * @throws WrongCommandFormatException when input command is not in correct format
     */
    public DeadlineCommand(String complexString) throws WrongCommandFormatException {

        try {
            int identifierIndex = FormatChecker.findIdentifierIndex(DEADLINE_IDENTIFIER, complexString);
            this.description = complexString.substring(0, identifierIndex);
            FormatChecker.checkNullString(this.description);
            this.time = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.time);
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
    }

    /**
     * get the command type of current deadline command
     *
     * @return command type of deadline command, DEADLINE
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * get the task that can be constructed from current deadline command
     *
     * @return the deadline task from current command
     */
    @Override
    public Task getTask() {
        return new Deadline(description, time);
    }
}
