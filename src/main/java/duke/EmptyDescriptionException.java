package duke;

/**
 * Signals an error caused by empty description of the command given by the user.
 */
public class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String message) {
            super(message);
        }
}
