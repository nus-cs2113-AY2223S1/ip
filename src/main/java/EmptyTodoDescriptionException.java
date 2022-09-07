
public class EmptyTodoDescriptionException extends DukeException {
    /**
     * Constructor for exception
     */

    public EmptyTodoDescriptionException(String message) {
        super(message);
    }

    /**
     * Message to be returned when this exception is caught
     *
     * @return message string
     */

    @Override
    public String getExceptionMessage() {
        return "☹ The description of a todo cannot be empty! Please try again.";
    }

}

