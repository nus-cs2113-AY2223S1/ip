package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


import duke.common.Messages;
import duke.data.exceptions.DukeException;
import duke.data.tag.TaskList;

import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;


/**
 * Represent a class that handle the parsing of command
 */
public class Parser {
    private TaskList taskList;

    /**
     * Parser instance take in the taskList to be manipulated
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse user input string
     *
     * @param userInput User input string
     * @return Command and its appropriate arguments
     */
    public Command parseCommand(String userInput) {
        String[] parsedInput = userInput.split(" ");
        if (parsedInput.length == 0) {
            return new IncorrectCommand(IncorrectCommand.MESSAGE);
        }

        final String commandWord = parsedInput[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord.toLowerCase()) {

        case TodoCommand.COMMAND_NAME:
            return parseTodoCommand(arguments);
        case DeadlineCommand.COMMAND_NAME:
            return parseDeadlineCommand(arguments);
        case EventCommand.COMMAND_NAME:
            return parseEventCommand(arguments);
        case MarkCommand.COMMAND_NAME:
            return parseMarkCommand(arguments);
        case UnmarkCommand.COMMAND_NAME:
            return parseUnmarkCommand(arguments);
        case DeleteCommand.COMMAND_NAME:
            return parseDeleteCommand(arguments);
        case FindCommand.COMMAND_NAME:
            return parseFindCommand(arguments);
        case DateCommand.COMMAND_NAME:
            return parseDateCommand(arguments);
        case ListCommand.COMMAND_NAME:
            return new ListCommand();
        case ExitCommand.COMMAND_NAME:
            return new ExitCommand();
        case HelpCommand.COMMAND_NAME: //Fall through
        default:
            return new HelpCommand();
        }
    }

    private Command parseTodoCommand(String arguments) {
        try {
            if (arguments.equals("")) {
                throw new DukeException();
            }
            return new TodoCommand(arguments);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {
            return new IncorrectCommand(TodoCommand.SYNTAX);
        }

    }

    private Command parseDeadlineCommand(String arguments) {
        try {
            String[] parsed = arguments.split("/");
            if (!(parsed.length == 2)) {
                throw new DukeException();
            }
            return new DeadlineCommand(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(DeadlineCommand.SYNTAX);
        }
    }

    private Command parseEventCommand(String arguments) {
        try {
            String[] parsed = arguments.split("/");
            if (!(parsed.length == 2)) {
                throw new DukeException();
            }
            return new EventCommand(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(EventCommand.SYNTAX);
        }
    }

    private Command parseMarkCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new MarkCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(MarkCommand.SYNTAX);
        }
    }


    private Command parseUnmarkCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new UnmarkCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(UnmarkCommand.SYNTAX);
        }
    }

    private Command parseDeleteCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new DeleteCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {
            return new IncorrectCommand(DeleteCommand.SYNTAX);
        }
    }

    private Command parseFindCommand(String arguments) {
        try {
            if (arguments.equals("")) {
                throw new DukeException();
            }
            return new FindCommand(arguments);
        } catch (DukeException e) {
            return new IncorrectCommand(FindCommand.SYNTAX);
        }
    }

    private Command parseDateCommand(String arguments) {
        try {
            return new DateCommand(LocalDate.parse(arguments));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(DateCommand.SYNTAX);
        }
    }

    private int[] strToIntArray(String[] parsed) throws DukeException {
        int[] intParsed = new int[parsed.length];
        for (int i = 0; i < parsed.length; i++) {
            intParsed[i] = Integer.parseInt(parsed[i]) - Messages.OFFSET;
            if (intParsed[i] < 0 | intParsed[i] > taskList.data.size()) {
                throw new DukeException();
            }
        }
        return intParsed;
    }
}
