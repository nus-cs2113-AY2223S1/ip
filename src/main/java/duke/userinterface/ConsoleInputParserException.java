package duke.userinterface;

import duke.DukeException;

public class ConsoleInputParserException {
    public static final String ERROR_MESSAGE_ARGUMENT_NOT_INTEGER = "The argument provided is not a valid integer.";
    public static final String ERROR_MESSAGE_COMMAND_TODO_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: todo DESCRIPTION";
    public static final String ERROR_MESSAGE_COMMAND_DEADLINE_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: deadline DESCRIPTION /by dd/MM/yyyy HHmm";
    public static final String ERROR_MESSAGE_COMMAND_EVENT_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: event DESCRIPTION /at dd/MM/yyyy HHmm dd/MM/yyyy HHmm";

    public static class CommandNotFoundException extends DukeException {
        public CommandNotFoundException() {
        }

        public CommandNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandMarkException extends DukeException {
        public InvalidCommandMarkException() {
        }

        public InvalidCommandMarkException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandUnmarkException extends DukeException {
        public InvalidCommandUnmarkException() {
        }

        public InvalidCommandUnmarkException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandTodoException extends DukeException {
        public InvalidCommandTodoException() {
        }

        public InvalidCommandTodoException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandDeadlineException extends DukeException {
        public InvalidCommandDeadlineException() {
        }

        public InvalidCommandDeadlineException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandEventException extends DukeException {
        public InvalidCommandEventException() {
        }

        public InvalidCommandEventException(String message) {
            super(message);
        }
    }

    public static class InvalidCommandDeleteException extends DukeException {
        public InvalidCommandDeleteException() {
        }

        public InvalidCommandDeleteException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when command find is not valid.
     */
    public static class InvalidCommandFindException extends DukeException {
        public InvalidCommandFindException() {

        }

        public InvalidCommandFindException(String message) {
            super(message);
        }
    }
}
