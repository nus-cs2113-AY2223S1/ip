package duke.userinterface;

import duke.DukeException;

public class ConsoleInputParserException {
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
}
