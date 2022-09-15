package duke.ui.parser;
import duke.exception.NotIntegerException;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandDeadline extends Command {

    private static final int MIN_ARGUMENTS = 3;
    private static final ArrayList<String> FLAGS = new ArrayList<>(Arrays.asList("/by"));

    private String description = null;

    private String date = null;
    public CommandDeadline(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = MIN_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.DEADLINE;

    }


    @Override
    protected void checkArgument() throws NotIntegerException {

    }

    @Override
    protected void parse() {
        int indexOfByFlag = rawArguments.indexOf("/by");

        description = rawArguments.substring(0, indexOfByFlag - 1);
        date = rawArguments.substring(indexOfByFlag + "/by ".length());
    }



    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
