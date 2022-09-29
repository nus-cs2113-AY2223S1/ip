package Duke;

import Duke.Commands.AddTodoCommand;
import Duke.Commands.ByeCommand;
import Duke.Commands.Command;
import Duke.Commands.*;

import java.io.IOException;

/**
 * Parses user input to execute the appropriate commands,
 * and throws error when the command is invalid.
 */
public class Parser {

    /**
     * Returns the correct command to be executed depending on user's input command
     * @param input user's input command
     * @param taskList carries out user's command and stores user's tasks in a list
     */
    public static Command parseCommand(String input, TaskList taskList) throws IOException {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        case ByeCommand.BYE_COMMAND:
            return new ByeCommand();
        case ListCommand.LIST_COMMAND:
            return new ListCommand();
        case AddTodoCommand.TODO_COMMAND:
            return new AddTodoCommand(commandAndParams, taskList);
        case AddDeadlineCommand.DEADLINE_COMMAND:
            return new AddDeadlineCommand(commandAndParams, taskList);
        case AddEventCommand.EVENT_COMMAND:
            return new AddEventCommand(commandAndParams, taskList);
        case MarkTaskCommand.MARK_COMMAND:
            return new MarkTaskCommand(commandAndParams, taskList);
        case UnmarkTaskCommand.UNMARK_COMMAND:
            return new UnmarkTaskCommand(commandAndParams, taskList);
        case DeleteCommand.DELETE_COMMAND:
            return new DeleteCommand(commandAndParams, taskList);
        case FindCommand.FIND_COMMAND:
            return new FindCommand(commandAndParams, taskList);
        default:
            return new InvalidCommand();
        }
    }
}
