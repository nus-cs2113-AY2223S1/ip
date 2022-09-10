package duke.text;

public enum ErrorText {
    ERROR_INVALID_COMMAND("    Oh no, Doraemon didn't understand your command."),
    ERROR_INVALID_TASK_INDEX("    Nobita, the specified task does not exist in Doramon's 4D pocket."),
    ERROR_INVALID_STATUS_FORMAT("    Nobita, the format is: mark <taskIndex> or unmark <taskIndex>, e.g. mark 1."),
    ERROR_INVALID_TODO_FORMAT("    Nobita, the format is todo <description>, e.g. todo read books."),
    ERROR_INVALID_DEADLINE_FORMAT("    Nobita, the format is: deadline <description> /by <dueBy>, e.g. deadline marry Shizuka /by September 3rd."),
    ERROR_INVALID_EVENT_FORMAT("    Nobita, the format is: event <description> /at <eventTime>, e.g. event marry Shizuka /at September 3rd 10-12pm."),

    ERROR_INVALID_DELETE_FORMAT("    Nobita, the format is: delete <taskIndex>, e.g. delete 1."),

    ERROR_FILE_IO("    Doraemon encountered an issue when writing to Nobita's file:");

    final String text;

    ErrorText(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
