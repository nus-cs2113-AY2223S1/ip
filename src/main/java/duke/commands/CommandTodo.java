package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of a todos command after it has been recognised by the parser.
 */
public class CommandTodo {
    /**
     * Deconstructs the raw command into key phrases and checks them, then adds a new todos to the task list.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return Successful add message to the user.
     * @throws DukeException.IllegalTodoException If there is no valid name for the new task.
     */
    public static String processNewTodo(String command, TaskList taskList)
            throws DukeException.IllegalTodoException {
        int spacePosition = command.indexOf(" ");
        if(spacePosition < 0) {
            throw new DukeException.IllegalTodoException();
        }
        return taskList.addNewTodo(command.substring(spacePosition + 1), true);
    }
}
