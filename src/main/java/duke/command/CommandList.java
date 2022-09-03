package duke.command;
import duke.exception.*;

public class CommandList extends Command {

    private static final int NUM_OF_ARGUMENTS = 0;
    private static final String[] FLAGS = {};


    public CommandList(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.MAX_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.LIST;
    }


    @Override
    protected void checkArgument() throws NotIntegerException {
    }

    @Override
    protected void parse() {
    }
}
