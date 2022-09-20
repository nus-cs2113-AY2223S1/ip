package duke.data;

/**
 * For Common Messages
 */
public class Messages {

    public static final String PROMPT = ">>> ";
    public static final String EXIT = "Program ends";
    public static final String WELCOME = "Hello! I'm a chatbot Duke made by Than Duc Huy\n"
            + "Type the command to start interacting with Duke";
    public static final String COMMAND_LISTS = "Supported commands:\n"
    +"To create tasks: todo, deadline, event\n"
    +"To edit tasks: mark, unmark, delete\n"
    +"To view tasks: list\n"
    +"To exit: exit";
    public static final String DIVIDER = "===============================================================================";
    public static final String DIVIDER_LIST = "=====================================LIST======================================";
    public static final String UNKNOWN_COMMAND = "Unknown Command";

    public static final int OFFSET = 1;
    public static final String FILE_CREATED = "Storage file created";
    public static final String FILE_OVERWRITTEN = "Storage file overwritten";
    public static final String FILE_LOADED = "Storage file loaded";
    public static final String NO_FILE = "There is no existing file!";
    public static final String WRONG_FILE = "The data in storage file is invalid. Please delete the file!";
}
