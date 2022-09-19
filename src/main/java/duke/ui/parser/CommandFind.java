package duke.ui.parser;
import duke.exception.NotIntegerException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandFind extends Command {

    private static final int MIN_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private ArrayList<String> keywords = new ArrayList<>();

    public CommandFind(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = MIN_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.UNMARK;
    }

    @Override
    protected void checkArgument() throws NotIntegerException {

    }

    @Override
    protected void parse() {
        keywords.addAll(splitArguments);
    }

}
