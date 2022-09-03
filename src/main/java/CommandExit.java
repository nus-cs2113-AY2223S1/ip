import java.util.Arrays;

public class CommandExit extends Command {

    private static final int NUM_OF_ARGUMENTS = 0;
    private static final String[] FLAGS = {};


    public CommandExit(String rawArguments) {
        super.rawArguments = rawArguments;
        super.splitArguments = Arrays.stream(rawArguments.split(" ")).filter(e -> e.trim().length() > 0).toArray(String[]::new);
        super.MIN_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.MAX_ARGUMENTS = NUM_OF_ARGUMENTS;
        super.FLAGS = FLAGS;
        super.commandType = CommandType.EXIT;

    }

    @Override
    public void checkArgument() throws NotIntegerException{
    }

    @Override
    protected void parse() {
    }


}



