package duke.commands;

import duke.DukeException;
import duke.TaskManager;

public class CommandTodo {
    public static void processNewTodo(String command, TaskManager taskManager)
            throws DukeException.IllegalTodoException {
        int spacePosition = command.indexOf(" ");
        if(spacePosition < 0) {
            throw new DukeException.IllegalTodoException();
        }
        taskManager.addNewTodo(command.substring(spacePosition + 1), true);
    }
}
