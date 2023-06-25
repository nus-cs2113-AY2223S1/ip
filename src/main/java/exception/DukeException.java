package exception;

import ui.Ui;

/**
 * Handles the possible exceptions in the program
 */
public class DukeException extends Exception {
    final public static String TASK_TYPE_ERROR = "TaskTypeError";
    final public static String TODO_DESCRIPTION_ERROR = "TodoDescriptionError";
    final public static String EVENT_DESCRIPTION_ERROR = "EventDescriptionError";
    final public static String DEADLINE_DESCRIPTION_ERROR = "DeadlineDescriptionError";
    final public static String RESTORATION_FILE_CORRUPTED_ERROR = "RestorationFileCorrupted";
    final public static String INDEX_PARSE_ERROR = "IndexParseError";
    final public static String INDEX_OUT_OF_BOUND_ERROR = "IndexOutOfBound";
    final public static String CREATE_FILE_ERROR = "CreateFileError";
    final public static String RESTORE_FILE_ERROR = "RestoreFileError";
    final public static String UPDATE_FILE_ERROR = "UpdateFileError";
    private String errorType;

    public DukeException(String str) {
        super();
        errorType = str;
    }

    /**
     * Handles the possible exceptions in the program
     */
    public void handleError() {
        String errorMessage;
        switch (errorType) {
        case TASK_TYPE_ERROR:
            errorMessage = "     OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case TODO_DESCRIPTION_ERROR:
            errorMessage = "     OOPS!!! The description of a todo cannot be empty.";
            break;
        case EVENT_DESCRIPTION_ERROR:
            errorMessage = "     OOPS!!! The description of an event is wrong.";
            break;
        case DEADLINE_DESCRIPTION_ERROR:
            errorMessage = "     OOPS!!! The description of a deadline is wrong.";
            break;
        case RESTORATION_FILE_CORRUPTED_ERROR:
            errorMessage = "     OOPS!!! File restoration corrupted. Restoration process stopped.";
            break;
        case INDEX_PARSE_ERROR:
            errorMessage = "     OOPS!!! Index parse failed.";
            break;
        case INDEX_OUT_OF_BOUND_ERROR:
            errorMessage = "     OOPS!!! Index out of bound.";
            break;
        case CREATE_FILE_ERROR:
            errorMessage = "     OOPS!!! Create file error.";
            break;
        case RESTORE_FILE_ERROR:
            errorMessage = "     OOPS!!! Restore file error.";
            break;
        case UPDATE_FILE_ERROR:
            errorMessage = "     OOPS!!! Update file error.";
            break;
        default:
            errorMessage = "     OOPS!!! Some unknown errors happen.";
        }
        Ui.printErrorMessage(errorMessage);
    }
}