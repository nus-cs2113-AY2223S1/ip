public abstract class Command {
    private CommandType commandType;

    /**
     * get the type of the command
     *
     * @return type of command
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * get the task that is described in the command, if there is any
     *
     * @return default is an empty Todo object
     */
    public Task getTask() {
        return new Todo("", false);
    }

    /**
     * get the index of the task to be modified, if there is any
     *
     * @return default is 0
     */
    public int getIndex() {
        return 0;
    }

    /**
     * get key associated with task, if there is any
     *
     * @return default is null
     */
    public String getKey() {
        return "";
    }
}
