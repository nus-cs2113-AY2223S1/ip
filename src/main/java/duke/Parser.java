package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;
import duke.exception.IncorrectCommandException;

import java.io.IOException;

public class Parser {

    /**
     * Parses the commands and calls the command to be executed.
     *
     * @param input User input command as a single string.
     * @return Command that is parsed to execute.
     * @throws IncorrectCommandException When command is not recognised.
     * @throws NoSpecificTimeException When description after '/at' delimiter is not provided.
     * @throws EmptyDescriptionException When commands is split and description is empty.
     * @throws NoSpecificDeadlineException When description after '/by' delimiter is not provided.
     * @throws IOException When error relevant to input/output of file.
     */
    public Command parseCommand(String input) throws IncorrectCommandException, NoSpecificTimeException,
            EmptyDescriptionException, NoSpecificDeadlineException, IOException {

        // Split commands into 2 portion. The first index contains the command and the second index
        // contains the description.
        String[] commands = input.trim().split(" ", 2);

        String commandType = commands[0];


        switch (commandType) {
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(commands);
        case "list":
            return new ListCommand(commands);
        case "mark":
            return new MarkCommand(commands);
        case "delete":
            return new DeleteCommand(commands);
        case "unmark":
            return new UnmarkCommand(commands);
        case "bye":
            return new ByeCommand(commands);
        case "find":
            return new FindCommand(commands);
        default:
            throw new IncorrectCommandException();
        }
    }






}
