public class ErrorCommand extends Command{
    private final static CommandType commandType = CommandType.ERROR;

    /**
     * get the type of current error command
     *
     * @return command type of error command, ERROR
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
