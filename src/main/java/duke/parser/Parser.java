package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * A class that parses and analyses the input string from the user.
 */
public class Parser {
    private static final String EXIT_PREFIX = "bye";
    private static final String LIST_PREFIX = "list";
    private static final String TODO_PREFIX = "todo";
    private static final String EVENT_PREFIX = "event";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String MARK_PREFIX = "mark";
    private static final String UNMARK_PREFIX = "unmark";
    private static final String DELETE_PREFIX = "delete";
    private static final String FIND_PREFIX = "find";
    private static final String DEADLINE_DIVIDER = "/by";
    private static final String EVENT_DIVIDER = "/at";

    /**
     * Parses the input from user.
     *
     * @param input The input from user.
     * @return The corresponding command.
     * @throws DukeException If the command is unrecognizable.
     */
    public static Command parseCommand(String input) throws DukeException {
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
        case DELETE_PREFIX:
            return manageDeleteCommand(input);
        case FIND_PREFIX:
            return manageFindCommand(input);
        default:
            throw new DukeException("SORRY!!! I cannot understand this command");
        }
    }

    /**
     * Manages the exit command.
     *
     * @return A new ExitCommand.
     */
    private static Command manageExitCommand() {
        return new ExitCommand();
    }

    /**
     * Manages the list command.
     *
     * @return A new ListCommand.
     */
    private static Command manageListCommand() {
        return new ListCommand();
    }

    /**
     * Manages the add todo task command.
     * @param input The input string from user.
     * @return A new AddCommand.
     * @throws DukeException If the input is in the wrong format.
     */
    private static Command manageTodoCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: todo {description}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty" + SUGGESTED_FORMAT);
        }
        Todo todo = new Todo(analysedInput[1].trim());
        return new AddCommand(todo);
    }
    /**
     * Manages the add event task command.
     * @param input The input string from user.
     * @return A new AddCommand.
     * @throws DukeException If the input is in the wrong format.
     */
    private static Command manageEventCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: event {description} /at {time}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The event information cannot be empty" + SUGGESTED_FORMAT);
        }
        String[] analysedEvent = analysedInput[1].split(EVENT_DIVIDER);
        if (analysedEvent.length != 2) {
            throw new DukeException("OOPS!!! The event description or time is missing" + SUGGESTED_FORMAT);
        }
        if (analysedEvent[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! The event description cannot be empty" + SUGGESTED_FORMAT);
        }
        if (analysedEvent[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! The event time cannot be empty" + SUGGESTED_FORMAT);
        }
        if (!DateParser.isValidDate(analysedEvent[1].trim())) {
            throw new DukeException("OOPS!!! I cannot recognize the date\n" + "Follow this format: yyyy-mm-dd");
        }
        Event event = new Event(analysedEvent[0].trim(), DateParser.formatDateToString(analysedEvent[1].trim()));
        return new AddCommand(event);
    }
    /**
     * Manages the add deadline task command.
     * @param input The input string from user.
     * @return A new AddCommand.
     * @throws DukeException If the input is in the wrong format.
     */
    private static Command manageDeadlineCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: deadline {description} /by {time}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The deadline information cannot be empty" + SUGGESTED_FORMAT);
        }
        String[] analysedDeadline = analysedInput[1].split(DEADLINE_DIVIDER);
        if (analysedDeadline.length != 2) {
            throw new DukeException("OOPS!!! The deadline description or time is missing" + SUGGESTED_FORMAT);
        }
        if (analysedDeadline[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! The deadline description cannot be empty" + SUGGESTED_FORMAT);
        }
        if (analysedDeadline[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! The deadline due time cannot be empty" + SUGGESTED_FORMAT);
        }
        if (!DateParser.isValidDate(analysedDeadline[1].trim())) {
            throw new DukeException("OOPS!!! I cannot recognize the date\n" + "Follow this format: yyyy-mm-dd");
        }

        Deadline deadline = new Deadline(analysedDeadline[0].trim(), DateParser.formatDateToString(analysedDeadline[1].trim()));
        return new AddCommand(deadline);
    }
    /**
     * Manages the mark command.
     * @param input The input string from user.
     * @return A new MarkCommand.
     * @throws DukeException If the input is in the wrong format or the number or task is out of bound.
     */
    private static Command manageMarkCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: mark {task_number}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The task number to be marked done is missing" + SUGGESTED_FORMAT);
        }
        try {
            return new MarkCommand(Integer.parseInt(analysedInput[1].trim()));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please check the task number, only integer is accepted" + SUGGESTED_FORMAT);
        }
    }
    /**
     * Manages the unmark command.
     * @param input The input string from user.
     * @return A new UnmarkCommand.
     * @throws DukeException If the input is in the wrong format or the number or task is out of bound.
     */
    private static Command manageUnmarkCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: unmark {task_number}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The task number to be unmarked done is missing" + SUGGESTED_FORMAT);
        }
        try {
            return new UnmarkCommand(Integer.parseInt(analysedInput[1].trim()));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please check the task number, only integer is accepted" + SUGGESTED_FORMAT);
        }
    }
    /**
     * Manages the delete command.
     * @param input The input string from user.
     * @return A new DeleteCommand.
     * @throws DukeException If the input is in the wrong format or the number or task is out of bound.
     */
    private static Command manageDeleteCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: delete {task_number}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The task number to be deleted is missing" + SUGGESTED_FORMAT);
        }
        try {
            return new DeleteCommand(Integer.parseInt(analysedInput[1].trim()));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please check the task number, only integer is accepted" + SUGGESTED_FORMAT);
        }
    }
    /**
     * Manages the find command.
     * @param input The input string from user.
     * @return A new FindCommand.
     * @throws DukeException If the keyword is missing.
     */
    private static Command manageFindCommand(String input) throws DukeException {
        String SUGGESTED_FORMAT = "\nFollow this format: find {keyword}";
        String[] analysedInput = input.split(" ", 2);
        if (analysedInput.length != 2) {
            throw new DukeException("OOPS!!! The keyword is missing" + SUGGESTED_FORMAT);
        }
        return new FindCommand(analysedInput[1].trim());
    }
}
