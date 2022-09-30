package duke.ui.parser;

import duke.exception.DukeInvalidCommandTypeException;

public class Parser {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    /**
     * Default Constructor
     */
    public Parser() {
    }

    /**
     * Parses raw input into its Command object
     * @param rawInput The full line of text the user has input
     * @return Command The parsed command
     * @throws Exception All Exceptions
     */
    public static Command parse(String rawInput) throws Exception {
        String[] tokens = rawInput.split("[ \t]+");

        String commandType;

        try {
            commandType = tokens[0];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandTypeException("");
        }

        String rawArguments = tokens.length > 1 ? rawInput.substring(commandType.length()).trim() : "";

        Command command;

        switch (commandType) {
        case COMMAND_EXIT:
            command = new CommandExit(rawArguments);
            break;
        case COMMAND_LIST:
            command = new CommandList(rawArguments);
            break;
        case COMMAND_MARK:
            command = new CommandMark(rawArguments);
            break;
        case COMMAND_UNMARK:
            command = new CommandUnmark(rawArguments);
            break;
        case COMMAND_TODO:
            command = new CommandToDo(rawArguments);
            break;
        case COMMAND_DEADLINE:
            command = new CommandDeadline(rawArguments);
            break;
        case COMMAND_EVENT:
            command = new CommandEvent(rawArguments);
            break;
        case COMMAND_DELETE:
            command = new CommandDelete(rawArguments);
            break;
        case COMMAND_FIND:
            command = new CommandFind(rawArguments);
            break;
        default:
            throw new DukeInvalidCommandTypeException(commandType);
        }
        command.verifyAndParse();
        return command;
    }

}
