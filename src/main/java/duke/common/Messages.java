package duke.common;

/**
 * Defines messages used by program.
 */
public class Messages {
    public static final String CONSOLE_MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String CONSOLE_MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String CONSOLE_ERROR_ARGUMENT_NOT_INTEGER = "The argument provided is not a valid integer.";
    public static final String CONSOLE_ERROR_COMMAND_NOT_FOUND = "I'm sorry, but I don't know what that means :-(";
    public static final String CONSOLE_ERROR_COMMAND_TODO_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: todo DESCRIPTION";
    public static final String CONSOLE_ERROR_COMMAND_TODO_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
    public static final String CONSOLE_ERROR_COMMAND_DEADLINE_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
    public static final String CONSOLE_ERROR_COMMAND_EVENT_FORBIDDEN_CHARACTERS = "The arguments provided contain forbidden characters.";
    public static final String CONSOLE_ERROR_COMMAND_DEADLINE_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: deadline DESCRIPTION /by dd/MM/yyyy HHmm";
    public static final String CONSOLE_ERROR_COMMAND_EVENT_INVALID_SYNTAX = "The arguments are invalid. SYNTAX: event DESCRIPTION /at dd/MM/yyyy HHmm dd/MM/yyyy HHmm";
}
