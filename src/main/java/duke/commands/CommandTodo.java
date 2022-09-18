package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandTodo {
    public static String processNewTodo(String command, TaskList taskList)
            throws DukeException.IllegalTodoException {
        int spacePosition = command.indexOf(" ");
        if(spacePosition < 0) {
            throw new DukeException.IllegalTodoException();
        }
        return taskList.addNewTodo(command.substring(spacePosition + 1), true);
    }
}
