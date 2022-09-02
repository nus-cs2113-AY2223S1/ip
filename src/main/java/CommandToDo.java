public class CommandToDo extends Command {

    public static final int MIN_ARGUMENTS = 1;

    private String[] splitArguments;
    private String description = null;


    public CommandToDo(String rawArguments) {
        splitArguments = rawArguments.split(" ");
    }

    @Override
    public void checkArgumentLength() throws MissingArgumentException {
        if (splitArguments.length < MIN_ARGUMENTS) {
            throw new MissingArgumentException();
        }
    }

    @Override
    public void checkFlags() {

    }

    @Override
    public void checkArgument() {
    }

}
