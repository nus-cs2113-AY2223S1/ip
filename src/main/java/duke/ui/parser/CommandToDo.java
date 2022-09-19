package duke.ui.parser;
import duke.exception.*;

import java.util.ArrayList;

public class CommandToDo extends Command {

    private static final int MIN_ARGUMENTS = 1;
    private static final ArrayList<String> FLAGS = new ArrayList<>();

    private String description = null;

    public CommandToDo(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = MIN_ARGUMENTS;
        super.maxArguments = null;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.TODO;
    }

    @Override
    protected void checkArgumentLength() throws MissingArgumentException, ExtraArgumentException,
            MissingDescriptionException {

        if (super.minArguments != null && splitArguments.size() < MIN_ARGUMENTS) {
            throw new MissingDescriptionException();
        }
    }


    @Override
    protected void checkArgument() throws NotIntegerException {

    }

    @Override
    protected void parse() {
        description = rawArguments;
    }

    public String getDescription() {
        return description;
    }
}
