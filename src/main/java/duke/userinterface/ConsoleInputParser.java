package duke.userinterface;

import duke.commands.ConsoleCommand;
import duke.commands.ConsoleCommandBye;
import duke.commands.ConsoleCommandDeadline;
import duke.commands.ConsoleCommandDelete;
import duke.commands.ConsoleCommandEvent;
import duke.commands.ConsoleCommandFind;
import duke.commands.ConsoleCommandList;
import duke.commands.ConsoleCommandMark;
import duke.commands.ConsoleCommandTodo;
import duke.commands.ConsoleCommandUnmark;
import duke.common.Messages;
import duke.data.task.Task;
import duke.exceptions.ConsoleInputParserException;
import duke.storage.LocalStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input entered via standard input.
 */
@SuppressWarnings({"EnhancedSwitchMigration", "RedundantIfStatement"})
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

    private static boolean hasForbiddenCharacters(String input) {
        if (input.contains(LocalStorage.FORMAT_DELIMITER)) {
            return true;
        }

        return false;
    }

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
            throw new ConsoleInputParserException.InvalidCommandMarkException(Messages.CONSOLE_ERROR_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandUnmark parseCommandUnmark(String arguments) throws ConsoleInputParserException.InvalidCommandUnmarkException {
        try {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandUnmark(taskNumber);
        } catch (NumberFormatException e) {
            throw new ConsoleInputParserException.InvalidCommandUnmarkException(Messages.CONSOLE_ERROR_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandTodo parseCommandTodo(String arguments) throws ConsoleInputParserException.InvalidCommandTodoException, ConsoleInputParserException.ForbiddenCharactersCommandTodoException {
        if (arguments.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandTodoException(Messages.CONSOLE_ERROR_COMMAND_TODO_INVALID_SYNTAX);
        }
        if (hasForbiddenCharacters(arguments)) {
            throw new ConsoleInputParserException.ForbiddenCharactersCommandTodoException(Messages.CONSOLE_ERROR_COMMAND_TODO_FORBIDDEN_CHARACTERS);
        }

        return new ConsoleCommandTodo(arguments);
    }

    private static ConsoleCommandDeadline parseCommandDeadline(String arguments) throws ConsoleInputParserException.InvalidCommandDeadlineException, ConsoleInputParserException.ForbiddenCharactersCommandDeadlineException {
        String description;
        String by;
        try {
            String[] argumentArray = arguments.split("/by");
            description = argumentArray[0].trim();
            by = argumentArray[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(Messages.CONSOLE_ERROR_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        if (description.isEmpty() || by.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(Messages.CONSOLE_ERROR_COMMAND_DEADLINE_INVALID_SYNTAX);
        }
        if (hasForbiddenCharacters(description)) {
            throw new ConsoleInputParserException.ForbiddenCharactersCommandDeadlineException(Messages.CONSOLE_ERROR_COMMAND_DEADLINE_FORBIDDEN_CHARACTERS);
        }

        LocalDateTime byDateTime;
        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ConsoleInputParserException.InvalidCommandDeadlineException(Messages.CONSOLE_ERROR_COMMAND_DEADLINE_INVALID_SYNTAX);
        }

        return new ConsoleCommandDeadline(description, byDateTime);
    }

    private static ConsoleCommandEvent parseCommandEvent(String arguments) throws ConsoleInputParserException.InvalidCommandEventException, ConsoleInputParserException.ForbiddenCharactersCommandEventException {
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
            throw new ConsoleInputParserException.InvalidCommandEventException(Messages.CONSOLE_ERROR_COMMAND_EVENT_INVALID_SYNTAX);
        }

        if (description.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandEventException(Messages.CONSOLE_ERROR_COMMAND_EVENT_INVALID_SYNTAX);
        }
        if (hasForbiddenCharacters(description)) {
            throw new ConsoleInputParserException.ForbiddenCharactersCommandEventException(Messages.CONSOLE_ERROR_COMMAND_EVENT_FORBIDDEN_CHARACTERS);
        }

        LocalDateTime startAtDateTime;
        LocalDateTime endAtDateTime;
        try {
            startAtDateTime = LocalDateTime.parse(startAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            endAtDateTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ConsoleInputParserException.InvalidCommandEventException(Messages.CONSOLE_ERROR_COMMAND_EVENT_INVALID_SYNTAX);
        }

        return new ConsoleCommandEvent(description, startAtDateTime, endAtDateTime);
    }

    private static ConsoleCommandDelete parseCommandDelete(String arguments) throws ConsoleInputParserException.InvalidCommandDeleteException {
        try {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandDelete(taskNumber);
        } catch (NumberFormatException e) {
            throw new ConsoleInputParserException.InvalidCommandDeleteException(Messages.CONSOLE_ERROR_ARGUMENT_NOT_INTEGER);
        }
    }

    private static ConsoleCommandFind parseCommandFind(String arguments) throws ConsoleInputParserException.InvalidCommandFindException {
        if (arguments.isEmpty()) {
            throw new ConsoleInputParserException.InvalidCommandFindException(Messages.CONSOLE_ERROR_COMMAND_TODO_INVALID_SYNTAX);
        }

        return new ConsoleCommandFind(arguments);
    }

    /**
     * Parses user input.
     *
     * @param consoleInput Command and arguments entered by the user.
     * @return Parsed arguments for the corresponding commands.
     * @throws ConsoleInputParserException.CommandNotFoundException                    If the command is not found.
     * @throws ConsoleInputParserException.InvalidCommandMarkException                 If the format of the mark command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandUnmarkException               If the format of the unmark command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandTodoException                 If the format of the todo command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandDeadlineException             If the format of the deadline command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandEventException                If the format of the event command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandDeleteException               If the format of the delete command is not valid.
     * @throws ConsoleInputParserException.InvalidCommandFindException                 If the format of the find command is not valid.
     * @throws ConsoleInputParserException.ForbiddenCharactersCommandTodoException     If forbidden characters are found in the todo command.
     * @throws ConsoleInputParserException.ForbiddenCharactersCommandDeadlineException If forbidden characters are found in the deadline command.
     * @throws ConsoleInputParserException.ForbiddenCharactersCommandEventException    If forbidden characters are found in the event command.
     */
    public static ConsoleCommand parseConsoleInput(ConsoleInput consoleInput) throws
            ConsoleInputParserException.CommandNotFoundException,
            ConsoleInputParserException.InvalidCommandMarkException,
            ConsoleInputParserException.InvalidCommandUnmarkException,
            ConsoleInputParserException.InvalidCommandTodoException,
            ConsoleInputParserException.InvalidCommandDeadlineException,
            ConsoleInputParserException.InvalidCommandEventException,
            ConsoleInputParserException.InvalidCommandDeleteException,
            ConsoleInputParserException.InvalidCommandFindException,
            ConsoleInputParserException.ForbiddenCharactersCommandTodoException,
            ConsoleInputParserException.ForbiddenCharactersCommandDeadlineException,
            ConsoleInputParserException.ForbiddenCharactersCommandEventException {
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
            throw new ConsoleInputParserException.CommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
