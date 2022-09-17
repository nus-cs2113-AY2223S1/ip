package duke.userinterface;

public class ConsoleInputParser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    public static ConsoleCommand parseConsoleInput(ConsoleInput consoleInput) throws
            ConsoleInputParserException.InvalidCommandMarkException,
            ConsoleInputParserException.InvalidCommandUnmarkException,
            ConsoleInputParserException.InvalidCommandTodoException,
            ConsoleInputParserException.InvalidCommandDeadlineException,
            ConsoleInputParserException.InvalidCommandEventException,
            ConsoleInputParserException.InvalidCommandDeleteException,
            ConsoleInputParserException.CommandNotFoundException {
        String command = consoleInput.getCommand();
        String arguments = consoleInput.getArguments();

        if (command.equalsIgnoreCase(COMMAND_BYE)) {
            return new ConsoleCommandBye();
        } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
            return new ConsoleCommandList();
        } else if (command.equalsIgnoreCase(COMMAND_MARK)) {
            try {
                int taskNumber = Integer.parseInt(arguments);

                return new ConsoleCommandMark(taskNumber);
            } catch (NumberFormatException e) {
                throw new ConsoleInputParserException.InvalidCommandMarkException("The argument " + arguments + " is not a valid integer.");
            }
        } else if (command.equalsIgnoreCase(COMMAND_UNMARK)) {
            try {
                int taskNumber = Integer.parseInt(arguments);

                return new ConsoleCommandUnmark(taskNumber);
            } catch (NumberFormatException e) {
                throw new ConsoleInputParserException.InvalidCommandUnmarkException("The argument " + arguments + " is not a valid integer.");
            }
        } else if (command.equalsIgnoreCase(COMMAND_TODO)) {
            if (arguments.isEmpty()) {
                throw new ConsoleInputParserException.InvalidCommandTodoException("The arguments are invalid. SYNTAX: todo DESCRIPTION");
            }

            return new ConsoleCommandTodo(arguments);
        } else if (command.equalsIgnoreCase(COMMAND_DEADLINE)) {
            String description;
            String by;

            try {
                String[] argumentArray = arguments.split("/by");
                description = argumentArray[0].trim();
                by = argumentArray[1].trim();

            } catch (IndexOutOfBoundsException e) {
                throw new ConsoleInputParserException.InvalidCommandDeadlineException("The arguments are invalid. SYNTAX: deadline DESCRIPTION /at BY");
            }

            if (description.isEmpty() || by.isEmpty()) {
                throw new ConsoleInputParserException.InvalidCommandDeadlineException("The arguments are invalid. SYNTAX: deadline DESCRIPTION /at BY");
            }

            return new ConsoleCommandDeadline(description, by);
        } else if (command.equalsIgnoreCase(COMMAND_EVENT)) {
            String description;
            String at;
            try {
                String[] argumentArray = arguments.split("/at");
                description = argumentArray[0].trim();
                at = argumentArray[1].trim();

            } catch (IndexOutOfBoundsException e) {
                throw new ConsoleInputParserException.InvalidCommandEventException("The arguments are invalid. SYNTAX: event DESCRIPTION /at AT");
            }

            if (description.isEmpty() || at.isEmpty()) {
                throw new ConsoleInputParserException.InvalidCommandEventException("The arguments are invalid. SYNTAX: event DESCRIPTION /at AT");
            }

            return new ConsoleCommandEvent(description, at);
        } else if (command.equalsIgnoreCase(COMMAND_DELETE)) {
            try {
                int taskNumber = Integer.parseInt(arguments);

                return new ConsoleCommandDelete(taskNumber);
            } catch (NumberFormatException e) {
                throw new ConsoleInputParserException.InvalidCommandDeleteException("The argument " + arguments + " is not a valid integer.");
            }
        } else {
            throw new ConsoleInputParserException.CommandNotFoundException();
        }
    }
}
