package duke;

public final class Message {
    public static final String GREETING_MESSAGE = "Hello! I'm Ever\n" +
            "What can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HELP_MESSAGE = "Type 'help' if you need help.";
    public static final String EMPTY_INPUT_MESSAGE = "I can't hear anything from you, can you say it again?";
    public static final String INVALID_COMMAND_MESSAGE = "Sorry, I don't get what you mean. Can you try again?";
    public static final String NO_TASKS_MESSAGE = "There are no tasks added yet.";
    public static final String MAXIMUM_TASKS_REACHED_ERROR_MESSAGE = "Maximum number of tasks reached";

    /** Task Number Error Messages **/
    public static final String WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE = "Sorry, the task number is out of range. Type \"list\" for viewing all the tasks.";
    public static final String WRONG_TASK_NUMBER_ERROR_MESSAGE = "Sorry, the selected task has not been created yet. Type \"list\" to see the task numbers.";
    public static final String MISSING_TASK_NUMBER_ERROR_MESSAGE = "Sorry, you have not provide the task number.";
    public static final String WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE = "Invalid input. Please type an integer for the task number.";

    /** Add Task Error Messages **/
    public static final String INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE = "Invalid input, todo task could not be added";
    public static final String INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE = "Invalid input, please provide the description and deadline";
    public static final String INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE = "Invalid input, please provide the description and date time";

    private Message() {
    }
}
