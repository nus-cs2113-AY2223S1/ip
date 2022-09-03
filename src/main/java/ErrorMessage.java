public enum ErrorMessage {
    INVALID_COMMAND_ERROR_MESSAGE("I'm sorry, but I don't know what that means :-("),
    INVALID_MARK_OR_UNMARK_INDEX_ERROR_MESSAGE("The selected task index value does not exists."),
    TODO_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of a todo cannot be empty."),
    DEADLINE_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of a todo cannot be empty."),
    EVENT_MISSING_DESCRIPTION_ERROR_MESSAGE("The description of a todo cannot be empty."),
    EVENT_MISSING_TIME_ERROR_MESSAGE("Separator \" /at \" is not found."),
    DEADLINE_MISSING_TIME_ERROR_MESSAGE("Separator \" /by \" is not found."),
    BANNER("____________________________________________________________");

    final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
