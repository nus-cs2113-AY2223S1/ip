package duke.ui.parser;
import duke.exception.NotIntegerException;

import java.util.ArrayList;

public class CommandExit extends Command {

    private static final int NUM_OF_ARGUMENTS = 0;
    private static final ArrayList<String> FLAGS = new ArrayList<>();


    public CommandExit(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = NUM_OF_ARGUMENTS;
        super.maxArguments = NUM_OF_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.EXIT;

    }

    @Override
    public void checkArgument() throws NotIntegerException {
    }

    @Override
    protected void parse() {
    }


}



