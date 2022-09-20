package duke.ui.parser;
import duke.exception.DukeNotIntegerException;

import java.util.ArrayList;

public class CommandMark extends Command {

    private static final int NUM_OF_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private Integer taskNum = null;

    public CommandMark(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = NUM_OF_ARGUMENTS;
        super.maxArguments = NUM_OF_ARGUMENTS;
        super.flags = FLAGS;
        super.commandType = CommandType.MARK;
    }

    @Override
    public void checkArgumentType() throws DukeNotIntegerException {
        try {
            taskNum = Integer.parseInt(splitArguments.get(0));
        } catch (NumberFormatException e) {
            throw new DukeNotIntegerException(splitArguments.get(0));
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
