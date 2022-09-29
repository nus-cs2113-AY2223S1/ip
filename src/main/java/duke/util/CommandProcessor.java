package duke.util;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

import duke.exception.UnknownCommandException;
import duke.util.asset.Task;
import duke.util.asset.Deadline;
import duke.util.asset.Event;
import duke.util.asset.Todo;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.MarkedCommand;
import duke.command.UnmarkCommand;

public class CommandProcessor {

    public static boolean isValidCommand(String command) {
        switch(command) {
            case (Deadline.COMMAND):
            case (Event.COMMAND):
            case (Todo.COMMAND):
            case (DeleteCommand.COMMAND):
            case (ExitCommand.COMMAND):
            case (ListCommand.COMMAND):
            case (MarkCommand.COMMAND):
            case (MarkedCommand.COMMAND): //Fallthrough
            case (UnmarkCommand.COMMAND):
                return true;
            default:
                return false;
        }
    }

    public static boolean isCorrectArgumentLength(String command, List<String> arguments) {
        switch (command) {
            case (ListCommand.COMMAND):
            case (MarkedCommand.COMMAND):
            case (ExitCommand.COMMAND):
                return true;
            default:
                return arguments.size() > 1;
        }
    }

    public static Command createCommand(String command, ArrayList<String> parameters) throws DukeException {
        switch(command) {
        case (Deadline.COMMAND):
        case (Event.COMMAND): //Fallthrough
        case (Todo.COMMAND):
            Task taskToAdd = createTask(command, parameters);
            return new AddCommand(taskToAdd);
        case (DeleteCommand.COMMAND):
            return new DeleteCommand( Integer.parseInt(parameters.get(0)));
        case (ExitCommand.COMMAND):
            return new ExitCommand();
        case (ListCommand.COMMAND):
            return new ListCommand();
        case (MarkCommand.COMMAND):
            return new MarkCommand( Integer.parseInt(parameters.get(0)) );
        case (MarkedCommand.COMMAND):
            return new MarkedCommand();
        case (UnmarkCommand.COMMAND):
            return new UnmarkCommand( Integer.parseInt(parameters.get(0)) );
        default:
            throw new DukeException("Error: unknown command");
        }
    }

    //@author owenl131-reused
    //Reused from his ip with slight modification
    public static Task createTask(String command, ArrayList<String> parameters) throws DukeException {

        switch(command) {
            case (Deadline.COMMAND):
                return new Deadline(parameters.get(0), parameters.get(1));
            case (Event.COMMAND):
                return new Event(parameters.get(0), parameters.get(1));
            case (Todo.COMMAND):
                return new Todo(parameters.get(0));
            default:
                throw new UnknownCommandException("Error: cannot create task");
        }

    }
    //@author
}
