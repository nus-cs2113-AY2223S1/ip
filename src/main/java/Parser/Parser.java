package Parser;
import Exception.EmptyArgumentException;
import Exception.InvalidCommandException;

public class Parser {
    private Command command;
    private String userArgs = "";
    private boolean hasArgument = true;

    public Command getCommand() {
        return command;
    }

    public String getUserArgs() {
        return userArgs;
    }

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
        case "exit":
            hasArgument = false;
            return Command.EXIT;
        case "find":
            return Command.FIND;
        default:
            hasArgument = false;
            throw new InvalidCommandException();
        }
    }

}
