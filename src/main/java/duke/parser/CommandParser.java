package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.EmptyInputException;
import duke.exception.InvalidCommandException;

public class CommandParser extends Parser {
    /**
     * Parses the command and returns the corresponding Command object.
     * If the command is invalid, an InvalidCommandException is thrown.
     * @param fullCommand The full command entered by the user.
     * @return The corresponding Command object.
     * @throws InvalidCommandException If the command is invalid.
     * @throws EmptyInputException
     */
    public static Command parseCommand(String fullCommand) throws InvalidCommandException, EmptyInputException {
        String keyword = Parser.getKeyword(fullCommand);
        switch (keyword) {
        case ByeCommand.KEYWORD:
            return new duke.command.ByeCommand(fullCommand);
        case "list":
            return new duke.command.ListCommand(fullCommand);
        case "total":
            return new duke.command.TotalCommand(fullCommand);
        case "delete":
            return new duke.command.DeleteCommand(fullCommand);
        case "mark":
            return new duke.command.MarkCommand(fullCommand);
        case "unmark":
            return new duke.command.UnmarkCommand(fullCommand);
        case "todo":
            return new duke.command.TodoCommand(fullCommand);
        case "deadline":
            return new duke.command.DeadlineCommand(fullCommand);
        case "event":
            return new duke.command.EventCommand(fullCommand);
        case "find":
            return new duke.command.FindCommand(fullCommand);
        case "help":
            return new duke.command.HelpCommand(fullCommand);
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


