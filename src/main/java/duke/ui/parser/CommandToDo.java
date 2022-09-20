package duke.ui.parser;
import duke.exception.*;

import java.util.ArrayList;

public class CommandToDo extends Command {

    private static final int MIN_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private String description = null;

    /**
     * Constructor
     * @param rawArguments Portion of the raw user input that contains the raw arguments
     */
    public CommandToDo(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = MIN_ARGUMENTS;
        super.maxArguments = null;
        super.flags = FLAGS;
        super.commandType = CommandType.TODO;
    }

    @Override
    protected void checkArgumentLength() throws DukeMissingArgumentException, DukeExtraArgumentException,
            DukeMissingDescriptionException {

        if (rawArguments.length() == 0) {
            throw new DukeMissingDescriptionException();
        }

    }

    @Override
    protected void checkArgumentType() throws DukeNotIntegerException {

    }

    @Override
    protected void parse() {
        description = rawArguments;
    }

    /**
     * Getter method to get task description
     * @return Task Description
     */
    public String getDescription() {
        return description;
    }
}
