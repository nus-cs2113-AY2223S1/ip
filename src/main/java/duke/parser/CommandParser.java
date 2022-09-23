package duke.parser;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * The parser handles parsing of commands.
 */
public class CommandParser {

    /**
     * Parses a user input string as a command.
     * 
     * @param description User input string
     * @return The command corresponding to the user input
     * @throws DukeException Throws an exception if the input is malformed
     */
    public static Command parseCommand(String description) throws DukeException {
        String keyword = Parser.parseKeyword(description);
        switch (keyword) {
        case ExitCommand.KEYWORD:
            return new ExitCommand(description);
        case ListCommand.KEYWORD:
            return new ListCommand(description);
        case MarkCommand.KEYWORD:
            return new MarkCommand(description);
        case UnmarkCommand.KEYWORD:
            return new UnmarkCommand(description);
        case DeleteCommand.KEYWORD:
            return new DeleteCommand(description);
        case DeadlineTask.KEYWORD:
        case EventTask.KEYWORD:
        case TodoTask.KEYWORD:
            return new AddCommand(description);
        case FindCommand.KEYWORD:
            return new FindCommand(description);
        case HelpCommand.KEYWORD:
            return new HelpCommand(description);
        default:
            throw new DukeException(DukeException.getDidNotUnderstandMessage());
        }
    }
}
