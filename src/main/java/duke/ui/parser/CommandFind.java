package duke.ui.parser;
import duke.exception.DukeNotIntegerException;

import java.util.ArrayList;

public class CommandFind extends Command {

    private static final int MIN_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private String searchPhrase;

    /**
     * Constructor
     * @param rawArguments Portion of the raw user input that contains the raw arguments
     */
    public CommandFind(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = MIN_ARGUMENTS;
        super.flags = FLAGS;
        super.commandType = CommandType.FIND;
    }

    @Override
    protected void checkArgumentType() throws DukeNotIntegerException {

    }

    @Override
    protected void parse() {
        searchPhrase = rawArguments;
    }


    /**
     * Getter method to get search phrase
      * @return Search phrase
     */
    public String getSearchPhrase() {
        // Return new string to avoid editing searchPhrase string stored in CommandFind object
        return new String(searchPhrase);
    }

}
