package Parser;
import Exception.EmptyArgumentException;
import Exception.InvalidCommandException;

/**
 * Represents the class to decipher the user input to command and its arguments.
 */
public class Parser {
    private Command command;
    private String userArgs = "";
    private boolean hasArgument = true;

    /**
     * Return the command.
     *
     * @return Command entered.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Return the user arguments.
     *
     * @return User arguments entered.
     */
    public String getUserArgs() {
        return userArgs;
    }

    /**
     * Process the raw user input from ui to store the command and arguments to the members.
     *
     * @param userInput Raw user input.
     * @throws ArrayIndexOutOfBoundsException if the raw input is empty.
     * @throws EmptyArgumentException if the argument is empty.
     * @throws InvalidCommandException if the command is invalid.
     */
    public void parseCommand(String userInput)
            throws ArrayIndexOutOfBoundsException, EmptyArgumentException, InvalidCommandException {
        String[] inputSplit = userInput.split(" ", 2);
        command = decipherCommand(inputSplit[0]);
        if (hasArgument) {
            if (inputSplit[1].isEmpty()) {
                throw new EmptyArgumentException();
            }
            userArgs = inputSplit[1];
        }
    }

    /**
     * Return the command enum type based on the user input.
     *
     * @param input Command string input.
     * @return Command enum type.
     * @throws InvalidCommandException if the command is invalid.
     */
    private Command decipherCommand(String input) throws InvalidCommandException {
        switch (input) {
        case "list":
            hasArgument = false;
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "delete":
            return Command.DELETE;
        case "bye":
            hasArgument = false;
            return Command.EXIT;
        case "find":
            return Command.FIND;
        case "help":
            hasArgument = false;
            return Command.HELP;
        default:
            hasArgument = false;
            throw new InvalidCommandException();
        }
    }

}
