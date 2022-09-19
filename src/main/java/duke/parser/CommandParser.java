package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FilterCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;


/**
 * <code>CommandParser</code> is the class that parses a user input into a valid command.
 * Operations in the parser include splitting the command word from the user input,
 * creating a <code>Command</code> object if the command word given is valid,
 * and calling the methods in <code>Command</code> object to check and set the necessary parameters
 * such that the command is ready for execution.
 */
public class CommandParser implements Parser<Command> {
    public CommandParser() {
    }


    /**
     * Parsing the user input into a valid Command object and returns it.
     * The method will first split the command word from the parameters in the input,
     * then create a Command object, followed by checking and setting the parameters for the Command object.
     *
     * @param userInput The complete user input given by user.
     * @return A Command object created based on the user input.
     * @throws DukeException Exception triggered on invalid command word, or any invalid parameters.
     */
    @Override
    public Command parse(String userInput) throws DukeException {
        String[] splits = splitCommandAndParameters(userInput);
        Command command = retrieveCommand(splits[0]);
        command.checkAndSetParameters(splits[1]);
        return command;
    }

    /**
     * Split the full user input into command word and parameters
     *
     * @param userInput The complete user input given by user.
     * @return A string array storing [Command Word, Parameter]
     */
    private static String[] splitCommandAndParameters(String userInput) {
        String[] splits = userInput.split(" ", 2);
        // Standardise the splits array into size of two when no space is found in user input
        if (splits.length == 1) {
            return new String[]{splits[0], ""};
        }
        return splits;
    }

    /**
     * Create and return a Command object based on the command word given,
     * or throws an exception if the command word is not found in the valid list of commands.
     *
     * @param commandInput Command word
     * @return A Command object created based on the command word.
     * @throws DukeException Exception triggered on invalid command word
     */
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
        case FilterCommand.COMMAND_WORD:
            command = new FilterCommand();
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
