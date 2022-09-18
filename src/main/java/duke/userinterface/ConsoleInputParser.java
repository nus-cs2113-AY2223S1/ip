package duke.userinterface;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input entered via standard input.
 */
public class ConsoleInputParser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static ConsoleCommandBye parseCommandBye() {
        return new ConsoleCommandBye();
    }

    private static ConsoleCommandList parseCommandList() {
        return new ConsoleCommandList();
    }

    private static ConsoleCommandMark parseCommandMark(String arguments) throws ConsoleInputParserException.InvalidCommandMarkException {
        try {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandMark(taskNumber);
        } catch (NumberFormatException e) {
            throw new ConsoleInputParserException.InvalidCommandMarkException(ConsoleInputParserException.ERROR_MESSAGE_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandUnmark parseCommandUnmark(String arguments) throws ConsoleInputParserException.InvalidCommandUnmarkException {
        try {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandUnmark(taskNumber);
        } catch (NumberFormatException e) {
            throw new ConsoleInputParserException.InvalidCommandUnmarkException(ConsoleInputParserException.ERROR_MESSAGE_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandTodo parseCommandTodo(String arguments) throws ConsoleInputParserException.InvalidCommandTodoException {
        if (arguments.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandTodoException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_TODO_INVALID_SYNTAX);
        }

        return new ConsoleCommandTodo(arguments);
    }

    private static ConsoleCommandDeadline parseCommandDeadline(String arguments) throws ConsoleInputParserException.InvalidCommandDeadlineException {
        String description;
        String by;
        try {
            String[] argumentArray = arguments.split("/by");
            description = argumentArray[0].trim();
            by = argumentArray[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        if (description.isEmpty() || by.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        LocalDateTime byDateTime;
        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        return new ConsoleCommandDeadline(description, byDateTime);
    }

    private static ConsoleCommandEvent parseCommandEvent(String arguments) throws ConsoleInputParserException.InvalidCommandEventException {
        String description;
        String startAt;
        String endAt;
        try {
            String[] argumentArray = arguments.split("/at");
            description = argumentArray[0].trim();
            String at = argumentArray[1].trim();
            String[] atArray = at.split(" ");
            startAt = atArray[0] + " " + atArray[1];
            endAt = atArray[2] + " " + atArray[3];
        } catch (IndexOutOfBoundsException e) {
            throw new ConsoleInputParserException.InvalidCommandEventException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_EVENT_INVALID_SYNTAX);
        }

        if (description.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandEventException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_EVENT_INVALID_SYNTAX);
        }

        LocalDateTime startAtDateTime;
        LocalDateTime endAtDateTime;
        try {
            startAtDateTime = LocalDateTime.parse(startAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            endAtDateTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ConsoleInputParserException.InvalidCommandEventException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        return new ConsoleCommandEvent(description, startAtDateTime, endAtDateTime);
    }

    private static ConsoleCommandDelete parseCommandDelete(String arguments) throws ConsoleInputParserException.InvalidCommandDeleteException {
        try {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandDelete(taskNumber);
        } catch (NumberFormatException e) {
            throw new ConsoleInputParserException.InvalidCommandDeleteException(ConsoleInputParserException.ERROR_MESSAGE_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandFind parseCommandFind(String arguments) throws ConsoleInputParserException.InvalidCommandFindException {
        if (arguments.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandFindException(ConsoleInputParserException.ERROR_MESSAGE_COMMAND_TODO_INVALID_SYNTAX);
        }

        return new ConsoleCommandFind(arguments);
    }

    /**
     * Parses user input.
     *
     * @param consoleInput Command and arguments entered by the user.
     * @return Parsed arguments for the corresponding commands.
     * @throws ConsoleInputParserException.InvalidCommandMarkException     If the format of the mark command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandUnmarkException   If the format of the unmark command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandTodoException     If the format of the todo command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandDeadlineException If the format of the deadline command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandEventException    If the format of the event command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandDeleteException   If the format of the delete command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandFindException     If the format of the find command is not valid.
     * @throws ConsoleInputParserException.CommandNotFoundException        If the command is not found.
     */
    public static ConsoleCommand parseConsoleInput(ConsoleInput consoleInput) throws
            ConsoleInputParserException.InvalidCommandMarkException,
            ConsoleInputParserException.InvalidCommandUnmarkException,
            ConsoleInputParserException.InvalidCommandTodoException,
            ConsoleInputParserException.InvalidCommandDeadlineException,
            ConsoleInputParserException.InvalidCommandEventException,
            ConsoleInputParserException.InvalidCommandDeleteException,
            ConsoleInputParserException.InvalidCommandFindException,
            ConsoleInputParserException.CommandNotFoundException {
        String command = consoleInput.getCommand();
        String arguments = consoleInput.getArguments();

        switch (command) {
        case COMMAND_BYE:
            return parseCommandBye();
        case COMMAND_LIST:
            return parseCommandList();
        case COMMAND_MARK:
            return parseCommandMark(arguments);
        case COMMAND_UNMARK:
            return parseCommandUnmark(arguments);
        case COMMAND_TODO:
            return parseCommandTodo(arguments);
        case COMMAND_DEADLINE:
            return parseCommandDeadline(arguments);
        case COMMAND_EVENT:
            return parseCommandEvent(arguments);
        case COMMAND_DELETE:
            return parseCommandDelete(arguments);
        case COMMAND_FIND:
            return parseCommandFind(arguments);
        default:
            throw new ConsoleInputParserException.CommandNotFoundException();
        }
    }
}
