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

/**
 * Provides the creation of a command object based on the corresponding user input.
 */
public class Parser {
    /**
     * Parses the user input and creates a corresponding command object.
     *
     * @param input A line of input entered by the user.
     * @return A command object that has been initialised to the relevant command class.
     * @throws InvalidCommandException If an invalid command is found in the user input.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        // Splits the user input into two parts, i.e. the command and the description
        String[] inputTokens = splitInput(input);
        String inputCommand = inputTokens[0];
        String inputDescription = inputTokens[1];
        Command command = null;

        // Initialises command object based on the identified command request entered by the user
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
            // Throws an exception to indicate an invalid command has been entered
            throw new InvalidCommandException();
        }

        return command;
    };

}
