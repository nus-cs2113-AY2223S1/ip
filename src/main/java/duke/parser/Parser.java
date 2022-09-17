package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

public class Parser {
    public static Command parse(String userInput) throws DukeException {
        String[] splits = splitCommandAndParameters(userInput);
        Command command = retrieveCommand(splits[0]);
        command.checkAndSetParameters(splits[1]);
        return command;
    }

    private static String[] splitCommandAndParameters(String userInput) {
        String[] splits = userInput.split(" ", 2);
        // Standardise the splits array into size of two when no space is found in user input
        if (splits.length == 1) {
            return new String[]{splits[0], ""};
        }
        return splits;
    }

    private static Command retrieveCommand(String commandInput) throws DukeException {
        String commandChecker = commandInput.toUpperCase();
        Command command;
        switch (commandChecker) {
        case TodoCommand.COMMAND_WORD:
            command = new TodoCommand();
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = new DeadlineCommand();
            break;
        case EventCommand.COMMAND_WORD:
            command = new EventCommand();
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand();
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case MarkCommand.COMMAND_WORD:
            command = new MarkCommand();
            break;
        case UnmarkCommand.COMMAND_WORD:
            command = new UnmarkCommand();
            break;
        case ByeCommand.COMMAND_WORD:
            command = new ByeCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
