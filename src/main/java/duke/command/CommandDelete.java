package duke.command;

import duke.exception.NotIntegerException;

public class CommandDelete extends Command {

    private static final int NUM_OF_ARGUMENTS = 1;
    private static final String[] FLAGS = {};

    private Integer taskNum = null;

    public CommandDelete(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.MAX_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.DELETE;
    }

    @Override
    public void checkArgument() throws NotIntegerException {
        try {
            taskNum = Integer.parseInt(splitArguments[0]);
        } catch (NumberFormatException e) {
            throw new NotIntegerException();
        }
    }

    @Override
    protected void parse() {
        taskNum = Integer.parseInt(splitArguments[0]);
    }

    public int getTaskNum() {
        return taskNum;
    }
}
