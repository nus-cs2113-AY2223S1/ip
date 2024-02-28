public class FindCommand  extends Command {
    private final CommandType commandType = CommandType.FIND;
    private final String key;

    public FindCommand(String key) {
        this.key = key;
    }

    /**
     * get the keyword to search for in the find command
     *
     * @return the keyword
     */
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
