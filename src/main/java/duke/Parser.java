package duke;

import duke.command.*;
import duke.exception.*;

import java.io.IOException;

public class Parser {



    public Command parseCommand(String input) throws IncorrectCommandException, NoSpecficTimeException,
            EmptyDescriptionException, NoSpecificDeadlineException, IOException {


        String[] commands = input.trim().split(" ", 2);


        if (commands.length == 0) {
            throw new IncorrectCommandException();
        }

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
            return new ByeCommand();
        default:
            throw new IncorrectCommandException();
        }
    }






}
