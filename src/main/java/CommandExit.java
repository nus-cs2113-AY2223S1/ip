public class CommandExit extends Command {

    public static final int NUM_OF_ARGUMENTS = 0;

    private String[] splitArguments;

    public CommandExit(String rawArguments) {
        splitArguments = rawArguments.split(" ");
    }

    @Override
    public void checkArgumentLength() throws ExtraArgumentException {
        if (splitArguments.length > NUM_OF_ARGUMENTS) {
            throw new ExtraArgumentException();
        }
    }

    @Override
    public void checkFlags() {
    }

    @Override
    public void checkArgument() {
    }


}



