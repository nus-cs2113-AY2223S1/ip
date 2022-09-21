package duke.common;

public enum ErrorMessages {
    MESSAGE_ERROR_INVALID_COMMAND("Oh no, Doraemon didn't understand your command."),
    MESSAGE_ERROR_INVALID_TASK_INDEX("Nobita, the specified task does not exist in Doramon's 4D pocket."),
    MESSAGE_ERROR_INVALID_STATUS_FORMAT("Nobita, the format is: mark <taskIndex> or unmark <taskIndex>, e.g. mark 1."),
    MESSAGE_ERROR_INVALID_TODO_FORMAT("Nobita, the format is todo <description>, e.g. todo read books."),
    MESSAGE_ERROR_INVALID_DEADLINE_FORMAT("Nobita, the format is: deadline <description> /by <d/MM/yyyy HHmm> " +
            "(either date/time or both), e.g. deadline marry Shizuka /by 3/09/2022."),
    MESSAGE_ERROR_INVALID_EVENT_FORMAT("Nobita, the format is: event <description> /at <eventTime>, e.g. event marry Shizuka /at September 3rd 10-12pm."),
    MESSAGE_ERROR_INVALID_DELETE_FORMAT("Nobita, the format is: delete <taskIndex>, e.g. delete 1."),
    MESSAGE_ERROR_INVALID_FIND_FORMAT("Nobita, the format is: find <keywords>, e.g. find Nobita marry Shizuka."),
    MESSAGE_ERROR_FILE_IO("Doraemon encountered an issue when writing to Nobita's file:");

    public final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
