public class EventCommand extends Command implements FormatChecker {

    private final String description;
    private final String time;
    private final static CommandType commandType = CommandType.TASK;

    /**
     * Construct an event given an almost unprocessed input command
     *
     * @param complexString a complex string that contains both event description and time
     * @throws WrongCommandFormatException when input command is not in correct format
     */
    public EventCommand(String complexString) throws WrongCommandFormatException {
        try {
            int identifierIndex = FormatChecker.findIdentifierIndex(EVENT_IDENTIFIER, complexString);
            this.description = complexString.substring(0, identifierIndex);
            FormatChecker.checkNullString(this.description);
            this.time = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.time);
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
    }

    /**
     * get the task that can be constructed from current event command
     *
     * @return the event task from current command
     */
    @Override
    public Task getTask() {
        return new Event(description, time);
    }

    /**
     * get the command type of current event command
     *
     * @return command type of event command, EVENT
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }

}
