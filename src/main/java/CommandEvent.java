import java.util.Arrays;

public class CommandEvent extends Command {

    private static final int MIN_ARGUMENTS = 3;
    private static final String[] FLAGS = {"/at"};

    private String description = null;

    private String date = null;
    public CommandEvent(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = splitArguments(rawArguments);
        super.MIN_ARGUMENTS = MIN_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.EVENT;

    }


    @Override
    protected void checkArgument() throws NotIntegerException {

    }

    @Override
    protected void parse() {
        int indexOfAtFlag = rawArguments.indexOf("/at");

        description = rawArguments.substring(0, indexOfAtFlag);
        date = rawArguments.substring(indexOfAtFlag + "/at ".length());
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
