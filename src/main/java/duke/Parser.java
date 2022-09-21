package duke;

import duke.exception.DukeUnknownInputException;
import duke.command.*;

public class Parser {

    /**
     * Parses the input to create Commands
     *
     * @param input User input from UI
     * @return specific command from user
     * @throws DukeUnknownInputException Exception if user command does not match any built in commands
     */
    public static Command parse(String input) throws DukeUnknownInputException {
        Command command = null;
        String[] splitInput = input.split(" ");

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
            case "find":
                command = new FindCommand(input);
                break;
            case "bye":
                command = new ByeCommand();
                break;
            default:
                throw new DukeUnknownInputException();
        }
        return command;
    }
}
