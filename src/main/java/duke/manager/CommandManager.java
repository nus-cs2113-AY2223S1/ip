package duke.manager;

import duke.exception.DukeException;
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
    private static final String DEADLINE_DIVIDER = "/by";
    private static final String EVENT_DIVIDER = "/at";


    public static Command manageCommand(String input) throws DukeException {
        input = input.trim();
        String[] mainCommand = input.split(" ", 2);
        switch (mainCommand[0].toLowerCase()) {
        case EXIT_PREFIX:
            return manageExitCommand();
        case LIST_PREFIX:
            return manageListCommand();
        case TODO_PREFIX:
            return manageTodoCommand(input);
        case EVENT_PREFIX:
            return manageEventCommand(input);
        case DEADLINE_PREFIX:
            return manageDeadlineCommand(input);
        case MARK_PREFIX:
            return manageMarkCommand(input);
        case UNMARK_PREFIX:
            return manageUnmarkCommand(input);
        default:
            throw new DukeException("SORRY!!! I cannot understand this command ☹");
        }
    }

    private static Command manageExitCommand() {
        return new ExitCommand();
    }

    private static Command manageListCommand() {
        return new ListCommand();
    }

    private static Command manageTodoCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: todo {description}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        Todo todo = new Todo(analysedInput[1].trim());
        return new AddCommand(todo);
    }

    private static Command manageEventCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: event {description} /at {time}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The event information cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        String[] analysedEvent = analysedInput[1].split(EVENT_DIVIDER);
        if (analysedEvent.length != 2) {
            throw new DukeException("OOPS!!! The event description or time is missing ☹" + SUGGESTED_FORMAT);
        }
        if (analysedEvent[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! The event description cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        if (analysedEvent[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! The event time cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        Event event = new Event(analysedEvent[0].trim(), analysedEvent[1].trim());
        return new AddCommand(event);
    }

    private static Command manageDeadlineCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: deadline {description} /by {time}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The deadline information cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        String[] analysedDeadline = analysedInput[1].split(DEADLINE_DIVIDER);
        if (analysedDeadline.length != 2) {
            throw new DukeException("OOPS!!! The deadline description or time is missing ☹" + SUGGESTED_FORMAT);
        }
        if (analysedDeadline[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! The deadline description cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        if (analysedDeadline[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! The deadline due time cannot be empty ☹" + SUGGESTED_FORMAT);
        }
        Deadline deadline = new Deadline(analysedDeadline[0].trim(), analysedDeadline[1].trim());
        return new AddCommand(deadline);
    }

    private static Command manageMarkCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: mark {task_number}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The task number to be marked done is missing ☹" + SUGGESTED_FORMAT);
        }
        try {
            return new MarkCommand(Integer.parseInt(analysedInput[1].trim()));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please check the task number, only integer is accepted ☹" + SUGGESTED_FORMAT);
        }
    }

    private static Command manageUnmarkCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: unmark {task_number}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The task number to be unmarked done is missing ☹" + SUGGESTED_FORMAT);
        }
        try {
            return new UnmarkCommand(Integer.parseInt(analysedInput[1].trim()));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please check the task number, only integer is accepted ☹" + SUGGESTED_FORMAT);
        }
    }
}
