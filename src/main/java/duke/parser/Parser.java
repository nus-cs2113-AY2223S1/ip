package duke.parser;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import static duke.command.Command.*;
import static duke.command.Command.tryDeleteTask;

public class Parser {

    public static Command parse(String fullCommand) {
        String[] parsedInput = fullCommand.split(" ");
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
            return new TodoCommand(fullCommand);
            break;
        case "deadline":
            return new DeadlineCommand(fullCommand);
            break;
        case "event":
            return new EventCommand(fullCommand);
            break;
        case "bye":
            printByeMessage();
            break untilBye;
        case "delete":
            tryDeleteTask(tasks, parsedInput);
            break;
        default:
            throw new DukeException();
            // Fallthrough
        }
    }
}
