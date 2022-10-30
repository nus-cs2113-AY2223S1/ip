package duke.util;

import java.util.ArrayList;
import java.util.List;

import duke.util.asset.Task;
import duke.util.asset.Deadline;
import duke.util.asset.Event;
import duke.util.asset.Todo;

import duke.command.*;

import duke.exception.UnknownCommandException;

/**
 * A class to process the commands
 * Checks the validity of the commands and assist parser in constructing the corresponding command instance
 * Throws UnknownCommandException when an unknown command is received
 */
public class CommandProcessor {

    /**
     * Check if a command is valid
     *
     * @param command the user command
     * @return if the command is valid
     */
    public static boolean isValidCommand(String command) {
        switch(command) {
        case (Deadline.COMMAND):
        case (Event.COMMAND):
        case (Todo.COMMAND):
        case (DeleteCommand.COMMAND):
        case (ExitCommand.COMMAND):
        case (HelpCommand.COMMAND):
        case (FindCommand.COMMAND):
        case (ListCommand.COMMAND):
        case (MarkCommand.COMMAND):
        case (MarkedCommand.COMMAND): //Fallthrough
        case (UnmarkCommand.COMMAND):
            return true;
        default:
            return false;
        }
    }

    /**
     * Check if the command requires any description/ index/ option etc
     *
     * Commands such as "list" are standalone and do not require arguments
     *
     * @param command user command
     * @param arguments list of arguments following the command
     * @return if the command has the right accompanying number of argument
     */
    public static boolean isCorrectArgumentLength(String command, List<String> arguments) {
        switch (command) {
        case (HelpCommand.COMMAND):
        case (ListCommand.COMMAND):
        case (MarkedCommand.COMMAND): //Fallthrough
        case (ExitCommand.COMMAND):
            return true;
        default:
            return arguments.size() > 1;
        }
    }

    /**
     * Construct the corresponding command class with parameters
     *
     * @param command the command to execute
     * @param parameters parsed parameters
     * @return command containing information to be executed
     * @throws UnknownCommandException if the command is not found
     */
    public static Command createCommand(String command, ArrayList<String> parameters) throws UnknownCommandException {
        switch(command) {
        case (Deadline.COMMAND):
        case (Event.COMMAND): //Fallthrough
        case (Todo.COMMAND):
            Task taskToAdd = createTask(command, parameters);
            return new AddCommand(taskToAdd);
        case (DeleteCommand.COMMAND):
            try {
                return new DeleteCommand(Integer.parseInt(parameters.get(0)));
            } catch (NumberFormatException e) {
                throw new UnknownCommandException("Error: Task to delete should be an index number");
            }
        case (ExitCommand.COMMAND):
            return new ExitCommand();
        case (FindCommand.COMMAND):
            return new FindCommand(parameters.get(0));
        case (HelpCommand.COMMAND):
            return new HelpCommand();
        case (ListCommand.COMMAND):
            return new ListCommand();
        case (MarkCommand.COMMAND):
            try {
                return new MarkCommand(Integer.parseInt(parameters.get(0)));
            } catch (NumberFormatException e){
                throw new UnknownCommandException("Error: Task to mark should be the index number");
            }
        case (MarkedCommand.COMMAND):
            return new MarkedCommand();
        case (UnmarkCommand.COMMAND):
            try {
                return new UnmarkCommand(Integer.parseInt(parameters.get(0)));
            } catch (NumberFormatException e){
                throw new UnknownCommandException("Error: Task to unmark should be the index number");
            }
        default:
            throw new UnknownCommandException("Error: unknown command");
        }
    }

    //@author owenl131-reused
    //Reused from his ip TaskFactory class with slight modification

    /**
     * Create the task instance to be added to the task list
     *
     * @param command the type to task to be created
     * @param parameters the parameters for the task
     * @return the Task subclass instance to be encapculated into the AddCommand instance
     * @throws UnknownCommandException
     */
    public static Task createTask(String command, ArrayList<String> parameters) throws UnknownCommandException {
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
