package Duke;

import Duke.Commands.AddTodoCommand;
import Duke.Commands.ByeCommand;
import Duke.Commands.Command;
import Duke.Commands.*;

import java.io.IOException;

public class Parser {
    public static Command parseCommand(String input, TaskList taskList) throws IOException {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddTodoCommand(commandAndParams[1], taskList);
        case "deadline":
            return new AddDeadlineCommand(commandAndParams[1], taskList);
        case "event":
            return new AddEventCommand(commandAndParams[1], taskList);
        case "mark":
            return new MarkTaskCommand(commandAndParams, taskList);
        case "unmark":
            return new UnmarkTaskCommand(commandAndParams, taskList);
        case "delete":
            return new DeleteCommand(commandAndParams, taskList);
        case "find":
            return new FindCommand(commandAndParams, taskList);
        default:
            return new InvalidCommand();
        }
    }
}
