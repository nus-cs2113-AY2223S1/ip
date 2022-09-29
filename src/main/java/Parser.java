public class Parser implements FormatChecker {

    private static final int TODO_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;
    private static final String COMMAND_SEPARATOR = " ";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_NULL = "";
    private static final String COMMAND_BYE = "bye";

    /**
     * Parse the full command input into different type of command
     *
     * @param input the string of full command input
     * @return a command that contains information of input
     * @throws NullCommandException    when the input is a null command
     * @throws UnknownCommandException when the input is not any known command
     */
    public static Command parseCommand(String input) throws NullCommandException, UnknownCommandException {
        String[] args = input.split(COMMAND_SEPARATOR);

        switch (args[0]) {
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
        case COMMAND_UNMARK:
            try {
                String commandType = args[0];
                int index = Integer.parseInt(args[1]) - 1;
                FormatChecker.checkExcessArgument(input);
                if (commandType.equalsIgnoreCase(COMMAND_MARK)) {
                    return new MarkCommand(index);
                }
                return new UnmarkCommand(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ExcessArgumentException e) {
                System.out.format("Exception: Wrong command Format%n" +
                        "Try the command in correct format: mark <index of task>%n");
            }
            break;
        case COMMAND_TODO:
            try {
                if (input.length() == TODO_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                return new TodoCommand(input.substring(TODO_LENGTH));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "todo <description>%n");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                if (input.length() == DEADLINE_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                return new DeadlineCommand(input.substring(DEADLINE_LENGTH));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "deadline <description> /by: <time>%n");
            }
            break;
        case COMMAND_EVENT:
            try {
                if (input.length() == EVENT_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                return new EventCommand(input.substring(EVENT_LENGTH));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "event <description> /at: <time>%n");
            }
            break;
        case COMMAND_DELETE:
            try {
                int index = Integer.parseInt(args[1]) - 1;
                FormatChecker.checkExcessArgument(input);
                return new DeleteCommand(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ExcessArgumentException e) {
                System.out.format("Exception: Wrong command Format%n" +
                        "Try the command in correct format: mark <index of task>%n");
            }
            break;
        case COMMAND_BYE:
            return new CommandBye();
        case COMMAND_NULL:
            throw new NullCommandException();
        default:
            throw new UnknownCommandException();
        }
        return new ErrorCommand();
    }
}
