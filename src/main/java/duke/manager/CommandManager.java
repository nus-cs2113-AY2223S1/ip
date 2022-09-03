package duke.manager;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.UI;

public class CommandManager {
    private static UI ui;
    private static final String EXIT_PREFIX = "bye";
    private static final String LIST_PREFIX = "list";
    private static final String TODO_PREFIX = "todo";
    private static final String EVENT_PREFIX = "event";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String MARK_PREFIX = "mark";
    private static final String UNMARK_PREFIX = "unmark";
    private static final String DEADLINE_DIVIDER = " /by ";
    private static final String EVENT_DIVIDER = " /at ";


    public static Command manageCommand(String input) {
        input = input.trim();
        String[] mainCommand = input.split(" ", 2);
        switch (mainCommand[0].toLowerCase()) {
        case EXIT_PREFIX:
            return manageExitCommand();
        case LIST_PREFIX:
            return manageListCommand();
        case TODO_PREFIX:
            return manageTodoCommand(mainCommand[1]);
        case EVENT_PREFIX:
            return manageEventCommand(mainCommand[1]);
        case DEADLINE_PREFIX:
            return manageDeadlineCommand(mainCommand[1]);
        case MARK_PREFIX:
            return manageMarkCommand(mainCommand[1]);
        case UNMARK_PREFIX:
            return manageUnmarkCommand(mainCommand[1]);
        default:
            break;
        }
        return null;
    }

    private static Command manageExitCommand() {
        return new ExitCommand();
    }

    private static Command manageListCommand() {
        return new ListCommand();
    }

    private static Command manageTodoCommand(String input) {
        Todo todo = new Todo(input);
        return new AddCommand(todo);
    }

    private static Command manageEventCommand(String input) {
        String[] inputArr = input.split(EVENT_DIVIDER);
        Event event = new Event(inputArr[0], inputArr[1]);
        return new AddCommand(event);
    }

    private static Command manageDeadlineCommand(String input) {
        String[] inputArr = input.split(DEADLINE_DIVIDER);
        Deadline deadline = new Deadline(inputArr[0], inputArr[1]);
        return new AddCommand(deadline);
    }

    private static Command manageMarkCommand(String input) {
        return new MarkCommand(Integer.parseInt(input));
    }

    private static Command manageUnmarkCommand(String input) {
        return new UnmarkCommand(Integer.parseInt(input));
    }

}
