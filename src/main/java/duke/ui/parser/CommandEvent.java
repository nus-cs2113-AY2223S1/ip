package duke.ui.parser;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeNotIntegerException;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandEvent extends Command {

    private static final int MIN_ARGUMENTS = 3;
    private static final ArrayList<String> FLAGS = new ArrayList<>(Arrays.asList("/at"));

    private String description = null;

    private DukeDateTime date = null;
    public CommandEvent(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.minArguments = MIN_ARGUMENTS;
        super.flags = FLAGS;
        super.commandType = CommandType.EVENT;

    }


    @Override
    protected void checkArgumentType() throws DukeNotIntegerException {

    }

    @Override
    protected void parse() throws DukeDateTimeFormatException {
        int indexOfAtFlag = rawArguments.indexOf("/at");

        description = rawArguments.substring(0, indexOfAtFlag - 1);

        try {
            String dateString = rawArguments.substring(indexOfAtFlag + "/at ".length());
            date = new DukeDateTime(dateString);
        } catch (DateTimeException e) {
            throw new DukeDateTimeFormatException();
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
