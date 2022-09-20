package duke.parser;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.data.exception.InvalidCommandException;

import static duke.common.Utils.splitInput;

public class Parser {
    public static Command parseCommand(String input) throws InvalidCommandException {
        String[] inputTokens = splitInput(input);
        String inputCommand = inputTokens[0];
        String inputDescription = inputTokens[1];
        Command command = null;

        switch (inputCommand) {
        case ByeCommand.COMMAND:
            command = new ByeCommand();
            break;
        case DeadlineCommand.COMMAND:
            command = new DeadlineCommand(inputDescription, false);
            break;
        case DeleteCommand.COMMAND:
            command = new DeleteCommand(inputDescription);
            break;
        case EventCommand.COMMAND:
            command = new EventCommand(inputDescription, false);
            break;
        case ListCommand.COMMAND:
            command = new ListCommand();
            break;
        case MarkCommand.COMMAND_MARK:
        case MarkCommand.COMMAND_UNMARK:
            command = new MarkCommand(inputCommand, inputDescription);
            break;
        case TodoCommand.COMMAND:
            command = new TodoCommand(inputDescription, false);
            break;
        default:
            throw new InvalidCommandException();
        }

        return command;
    };

}
