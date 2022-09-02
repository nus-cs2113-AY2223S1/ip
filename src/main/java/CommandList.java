public class CommandList extends Command {
    public static final int NUM_OF_ARGUMENTS = 0;
    public static final String[] FLAGS = new String[]{};

    private String[] splitArguments;

    public CommandList(String rawArguments) {
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
