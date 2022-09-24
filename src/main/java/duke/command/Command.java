package duke.command;

import duke.task.Task;

import java.util.ArrayList;

import static duke.task.TaskList.*;

public class Command {

    static boolean tryCommand(ArrayList<Task> tasks, String line, String[] parsedInput) throws DukeException {
        boolean isExit = false;
        switch (parsedInput[0]) {
        case "list":
            printTaskList(tasks);
            break;
        case "mark":
            tryMarkTask(tasks, line);
            break;
        case "unmark":
            tryUnmarkTask(tasks, line);
            break;
        case "todo":
            tryAddTodo(tasks, line);
            break;
        case "deadline":
            tryAddDeadline(tasks, line);
            break;
        case "event":
            tryAddEvent(tasks, line);
            break;
        case "bye":
            isExit = true;
            break;
        case "delete":
            tryDeleteTask(tasks, parsedInput);
            break;
        default:
            throw new DukeException();
            // Fallthrough
        }
        return isExit;
    }
}
