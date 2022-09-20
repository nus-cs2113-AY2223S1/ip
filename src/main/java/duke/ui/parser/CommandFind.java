package duke.ui.parser;
import duke.exception.NotIntegerException;

import java.util.ArrayList;

public class CommandFind extends Command {

    private static final int MIN_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private String searchPhrase;

    public CommandFind(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = MIN_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.FIND;
    }

    @Override
    protected void checkArgument() throws NotIntegerException {

    }

    @Override
    protected void parse() {
        searchPhrase = rawArguments;

    }

    public String getSearchPhrase() {
        return new String(searchPhrase);
    }

}
