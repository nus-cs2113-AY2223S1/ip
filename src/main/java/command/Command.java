package command;

import java.io.IOException;
import exception.CommandNotFoundException;
import exception.InsufficentArgumentsException;
import exception.TaskNotFoundException;
import task.TaskManager;

public class Command implements CommandInterface {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";

    private static TaskManager taskManager = new TaskManager();

    private final String command;
    private final String[] args;

    public Command(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Handles a command. Throws an CommandNotFoundException if the command does not exist.
     */
    public void handleCommand() throws CommandNotFoundException, InsufficentArgumentsException,
            TaskNotFoundException, IOException {
        switch (command) {
        case COMMAND_LIST:
            taskManager.listTasks();
            break;
        case COMMAND_MARK_TASK:
            taskManager.markTask(args);
            break;
        case COMMAND_UNMARK_TASK:
            taskManager.unmarkTask(args);
            break;
        case COMMAND_TODO:
            taskManager.addTask(COMMAND_TODO, args);
            break;
        case COMMAND_DEADLINE:
            taskManager.addTask(COMMAND_DEADLINE, args);
            break;
        case COMMAND_EVENT:
            taskManager.addTask(COMMAND_EVENT, args);
            break;
        case COMMAND_DELETE_TASK:
            taskManager.deleteTask(args);
            break;
        default:
            throw new CommandNotFoundException();

        }
    }
}
