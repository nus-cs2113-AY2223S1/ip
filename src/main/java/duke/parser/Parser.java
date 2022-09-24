package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    private static String keyword;
    private static String statement;
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        keyword = fullCommand.split(" ")[0];
        statement = fullCommand.replaceFirst(keyword + " ", "");
        switch (keyword) {
        case "list":
            return new ListCommand(keyword, statement);
        case "mark":
            return new MarkCommand(keyword, statement);
        case "unmark":
            return new UnmarkCommand(keyword, statement);
        case "todo":
            return new TodoCommand(keyword, statement);
        case "deadline":
            return new DeadlineCommand(keyword, statement);
        case "event":
            return new EventCommand(keyword, statement);
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(keyword, statement);
        default:
            throw new DukeException();
        }
    }
}
