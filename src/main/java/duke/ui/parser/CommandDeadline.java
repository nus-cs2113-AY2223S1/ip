package duke.ui.parser;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeNotIntegerException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandDeadline extends Command {

    private static final int MIN_ARGUMENTS = 3;
    private static final ArrayList<String> FLAGS = new ArrayList<>(Arrays.asList("/by"));

    private String description = null;
    private DukeDateTime date = null;

    public CommandDeadline(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = MIN_ARGUMENTS;
        super.flags = FLAGS;
        super.commandType = CommandType.DEADLINE;
    }


    @Override
    protected void checkArgumentType() throws DukeNotIntegerException {

    }

    @Override
    protected void parse() throws DukeDateTimeFormatException {
        int indexOfByFlag = rawArguments.indexOf("/by");

        description = rawArguments.substring(0, indexOfByFlag - 1);

        String dateTimeString = rawArguments.substring(indexOfByFlag + "/by ".length());

        try {
            date = new DukeDateTime(dateTimeString);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeFormatException(dateTimeString);
        }
    }



    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date.getFormattedDate();
    }

    public String getTime() {
        return date.getFormattedTime();
    }

}
