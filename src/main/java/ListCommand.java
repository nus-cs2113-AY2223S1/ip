public class ListCommand extends Command {
    private final CommandType commandType = CommandType.LIST;

    /**
     * get the type of current list command
     *
     * @return command type of list command, LIST
     */
    @Override
    public CommandType getCommandType() {
        return this.commandType;
    }

}
