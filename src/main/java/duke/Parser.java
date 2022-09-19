package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TaskType;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String userCommand = input.split(" ")[0];
        String arguments = input.substring(userCommand.length());
        switch (userCommand) {
        case "hi":
            return new HiCommand();
        case "help":
            return new HelpCommand();
        case "please":
            return new PleaseCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(arguments.trim());
        case "on":
            return new DateCommand(arguments.trim(), "on");
        case "before":
            return new DateCommand(arguments.trim(), "before");
        case "after":
            return new DateCommand(arguments.trim(), "after");
        case "mark":
            return new MarkCommand(arguments.trim());
        case "unmark":
            return new UnmarkCommand(arguments.trim());
        case "delete":
            return new DeleteCommand(arguments.trim());
        case "todo":
            return new AddCommand(TaskType.TODO, arguments.trim());
        case "event":
            return new AddCommand(TaskType.EVENT, arguments.trim());
        case "deadline":
            return new AddCommand(TaskType.DEADLINE, arguments.trim());
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

}
