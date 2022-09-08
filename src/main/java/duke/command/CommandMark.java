package duke.command;
import duke.exception.NotIntegerException;

import java.util.ArrayList;

public class CommandMark extends Command {

    private static final int NUM_OF_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private Integer taskNum = null;

    public CommandMark(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.MAX_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.MARK;
    }

    @Override
    public void checkArgument() throws NotIntegerException {
        try {
            taskNum = Integer.parseInt(splitArguments.get(0));
        } catch (NumberFormatException e) {
            throw new NotIntegerException();
        }
    }

    @Override
    protected void parse() {
        taskNum = Integer.parseInt(splitArguments.get(0));
    }

    public int getTaskNum() {
        return taskNum;
    }
}
