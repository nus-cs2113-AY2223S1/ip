package duke;

public final class Message {
    public static final String GREETING_MESSAGE = "Hello! I'm Ever\n" +
            "What can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HELP_MESSAGE = "Type 'help' if you need help.";
    public static final String EMPTY_INPUT_ERROR_MESSAGE = "I can't hear anything from you, can you say it again?";
    public static final String INVALID_COMMAND_MESSAGE = "Sorry, I don't get what you mean. Can you try again?";
    public static final String NO_TASKS_MESSAGE = "There are no tasks added yet.";

    /** Task Number Error Messages **/
    public static final String WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE =
            "Sorry, the task number is out of range. Type \"list\" for viewing all the tasks.";
    public static final String MISSING_TASK_NUMBER_ERROR_MESSAGE = "Sorry, you have not provide the task number.";
    public static final String WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE =
            "Invalid input. Please type an integer for the task number.";

    /** Add Task Error Messages **/
    public static final String INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE = "Invalid input, todo task could not be added";
    public static final String INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE =
            "Invalid input, please provide the description and deadline";
    public static final String INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE =
            "Invalid input, please provide the description and date time";

    // Find Task Message
    public static final String NO_MATCHING_TASKS_MESSAGE = "No matching tasks for the given search word.";
    public static final String MISSING_SEARCH_WORD_ERROR_MESSAGE = "Please provide a search word to find the task.";

    /** Save Task Messages **/
    public static final String SAVE_TASK_FAIL_ERROR_MESSAGE = "Fail to save tasks. Try again ";

    /** File handling messages **/
    public static final String CREATE_FILE_FAIL_ERROR_MESSAGE = "Fails to create the file";

    private Message() {
    }
}
