public class CommandBye extends Command {
    private final CommandType commandType = CommandType.BYE;

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
