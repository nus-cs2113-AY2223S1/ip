package consoleCommands;

import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.Task;

import java.util.ArrayList;

/**
 * Class to handle all command calls in programme
 * Takes in two params, command and arguments to carry out command calls
 * Contains boolean isExit, to control when to end the programme
 */
public class Command {
    public static final String COMMAND_UNMARKED = "unmark";
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    private String command;
    private String arguments;
    private boolean isExit = false;
    /**
     * @param command is the string that determines the command executed
     * @param arguments is the string that supplements the executed command with data (e.g date, descriptions)
     */
    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }
    /**
     * execute() is the method that calls the commands in the programme
     * End programme: bye
     * List tasks: list
     * Add todo: todo (name)
     * Add deadline: deadline (name) /by (date & time)
     * Add event: event (name) /at (date & time)
     * Mark/Unmark: mark/unmark (task number)
     * Delete: delete (task number)
     * Find: find (input text)
     * @param taskList is an ArrayList of tasks, passed into .execute() to be used in commands
     * @throws NotEnoughArgumentsException if the arguments provided is insufficient for the corresponding commands
     * @throws TaskDoesNotExistException if the index provided in certain commands is > size of taskList
     * @throws InvalidArgumentsException if the arguments provided is incorrect for the corresponding commands
     * @throws InvalidCommandException if command provided does not exist
     */
    public void execute(ArrayList<Task> taskList)
            throws NotEnoughArgumentsException, TaskDoesNotExistException, InvalidArgumentsException,
            InvalidCommandException {
        try {
            if (command.equalsIgnoreCase(COMMAND_BYE)) {
                isExit = true;
            } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
                TaskList.printList(taskList);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                TaskList.findTask(arguments, taskList);
            } else if (command.equalsIgnoreCase(COMMAND_MARKED) || command.equalsIgnoreCase(COMMAND_UNMARKED)) {
                TaskList.markStatus(command, arguments, taskList);
            } else if (command.equalsIgnoreCase(COMMAND_DELETE)) {
                TaskList.deleteTask(arguments, taskList);
            } else if (command.equalsIgnoreCase(COMMAND_TODO)) {
                TaskList.addTodo(arguments, taskList);
            } else if (command.equalsIgnoreCase(COMMAND_EVENT)) {
                TaskList.addEvent(arguments, taskList);
            } else if (command.equalsIgnoreCase(COMMAND_DEADLINE)) {
                TaskList.addDeadline(arguments, taskList);
            } else {
                Ui.printLine();
                throw new InvalidCommandException();
            }
        } catch (NotEnoughArgumentsException e) {
            throw new NotEnoughArgumentsException();
        } catch (TaskDoesNotExistException e) {
            throw new TaskDoesNotExistException();
        } catch (InvalidArgumentsException e) {
            throw new InvalidArgumentsException();
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        }
    }
    /**
     * Method determines if programme exits or not
     */
    public boolean isExit() {
        return isExit;
    }


}
