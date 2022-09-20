package duke;

import duke.exception.UnknownInputException;
import duke.command.*;

public class Parser {


    public static Command parse(String input) throws UnknownInputException {
        Command command = null;
        String[] splitInput = input.split(" ");

        // list commands duke to list all the tasks stored and their completion status
        // try at the start cos of the errors possibly
        switch (splitInput[0]) {
            case "list":
                command = new ListCommand();
                break;
            case "mark":
                command = new MarkCommand(input);
                break;
            case "unmark":
                command = new UnmarkCommand(input);
                break;
            case "todo":
                command = new AddTodoCommand(input);
                break;
            case "deadline":
                command = new AddDeadlineCommand(input);
                break;
            case "event":
                command = new AddEventCommand(input);
                break;
            case "delete":
                command = new DeleteCommand(input);
                break;
            case "bye":
                command = new ByeCommand();
                break;
            default:
                throw new UnknownInputException();
        }

        return command;
    }
}
