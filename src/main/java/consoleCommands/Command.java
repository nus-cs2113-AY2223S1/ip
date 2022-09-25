package consoleCommands;

import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.Task;

import java.util.ArrayList;

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
    public Command (String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }
    public void execute (ArrayList<Task> taskList)
            throws NotEnoughArgumentsException, TaskDoesNotExistException, InvalidArgumentsException,
            InvalidCommandException {
        try {
            if (command.equals(COMMAND_BYE)) {
                isExit = true;
            } else if (command.equals(COMMAND_LIST)) {
                TaskList.printList(taskList);
            } else if (command.equals(COMMAND_FIND)) {
                TaskList.findTask(arguments, taskList);
            } else if (command.equals(COMMAND_MARKED) || command.equals(COMMAND_UNMARKED)) {
                TaskList.markStatus(command, arguments, taskList);
            } else if (command.equals(COMMAND_DELETE)) {
                TaskList.deleteTask(arguments, taskList);
            } else if (command.equals(COMMAND_TODO)) {
                TaskList.addTodo(arguments, taskList);
            } else if (command.equals(COMMAND_EVENT)) {
                TaskList.addEvent(arguments, taskList);
            } else if (command.equals(COMMAND_DEADLINE)) {
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
    public boolean isExit () {
        return isExit;
    }


}
