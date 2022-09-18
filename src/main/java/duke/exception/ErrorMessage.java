package duke.exception;

public enum ErrorMessage {
    INVALID_COMMAND_ERROR_MESSAGE("I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX_ERROR_MESSAGE("The selected task index value does not exists."),
    INVALID_DATE_TIME_FORMAT_ERROR_MESSAGE("The time format provided is incorrect."),
    TODO_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of a todo cannot be empty."),
    DEADLINE_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of a deadline cannot be empty."),
    DEADLINE_MISSING_TAG_ERROR_MESSAGE("Separator \" /by \" is not found."),
    DEADLINE_MISSING_TIME_ERROR_MESSAGE("The time of a deadline cannot be empty."),
    EVENT_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of an event cannot be empty."),
    EVENT_MISSING_TAG_ERROR_MESSAGE("Separator \" /at \" is not found."),
    EVENT_MISSING_TIME_ERROR_MESSAGE("The time of an event cannot be empty."),
    STORAGE_INITIALIZATION_ERROR_MESSAGE("Something went wrong while reading the file."),
    STORAGE_OUTPUT_ERROR_MESSAGE("Something went wrong while writing to file.");

    final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
