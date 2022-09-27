package duke.common;

import duke.data.task.Task;

/**
 * Defines messages used by program.
 */
public class Messages {
    public static final String CONSOLE_MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String CONSOLE_MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String CONSOLE_ERROR_ARGUMENT_NOT_INTEGER = "The argument provided is not a valid integer.";
    public static final String CONSOLE_ERROR_COMMAND_NOT_FOUND = "I'm sorry, but I don't know what that means :-(";
    public static final String CONSOLE_ERROR_COMMAND_LIST_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: list [" + Task.DATE_FORMAT + "]";
    public static final String CONSOLE_ERROR_COMMAND_TODO_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: todo DESCRIPTION";
    public static final String CONSOLE_ERROR_COMMAND_TODO_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
    public static final String CONSOLE_ERROR_COMMAND_DEADLINE_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: deadline DESCRIPTION /by " + Task.DATE_TIME_FORMAT;
    public static final String CONSOLE_ERROR_COMMAND_DEADLINE_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
    public static final String CONSOLE_ERROR_COMMAND_EVENT_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: event DESCRIPTION /at " + Task.DATE_TIME_FORMAT + " " + Task.DATE_TIME_FORMAT;
    public static final String CONSOLE_ERROR_COMMAND_EVENT_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
}
