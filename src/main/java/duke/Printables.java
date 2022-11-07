package duke;

/**
 * A class containing the printable constant strings for the program.
 */
public class Printables {

    public static final String INITIAL_GREETING = "Greetings, I am Azmuth\n" +
            "What can I do for you?\n" +
            "Enter command 'help' for a list of useful commands!\n" +
            "____________________";

    public static final String GOODBYE_MESSAGE = "Bye and see you again soon!";

    public static final String HELP_MANUAL = "____________________\n" +
            "Here if a list of commands that I recognise!\n" +
            "Date format is 'yyyy-mm-dd' and Time format is 'hh:mm'\n\n" +
            "'todo <taskName>': Add a new todo task\n" +
            "'deadline <taskName>/<date> <time>': Add a new deadline task\n" +
            "'event <taskName>/<date> <time>': Add a new event task\n" +
            "'list': Lists out tasks in index order\n" +
            "'mark <taskNumber>': Mark a certain task as done\n" +
            "'unmark <taskNumber>': Unmark a certain task that was done\n" +
            "'delete <taskNumber>': Delete the task from the list\n" +
            "'find <something>': Searches the task list to find matching task names" +
            "'checkout <date>': Checkout what deadlines or events you have on  a specific date\n" +
            "'bye': Quit the system\n" + "____________________";

    public static final String INVALID_BASIC_COMMAND_MESSAGE = "Commands 'list', 'help' and 'bye' " +
            "can only be used alone as a single word";

    public static final String ALREADY_MARKED_MESSAGE = "This task has already been marked done!";
    public static final String ALREADY_UNMARKED_MESSAGE = "This task as already been unmarked!";
    public static final String NO_TASKS_IN_LIST_MESSAGE = "There are no tasks in your task list at the moment";
    public static final String INVALID_GENERAL_COMMAND = "This is an invalid command!";
    public static final String SUCCESSFUL_SAVE_MESSAGE = "Successfully saved your task list!";
    public static final String SUCCESSFUL_LOAD_MESSAGE = "Successfully loaded your last save of tasks to do!\n";
    public static final String FAIL_SAVE_MESSAGE = "Failed to save your task list!";
    public static final String MISSING_FILE_MESSAGE = "File not found, a new data.txt file will be created upon save!";
    public static final String TASK_SEARCH_INIT_STRING = "Here are the matching results from your task list!\n";
    public static final String EMPTY_TASK_SEARCH_RESULT_MESSAGE = "No relevant tasks were found\n";
    public static final String DATE_SEARCH_INIT_STRING = "Here are the corresponding deadlines and events!\n";
    public static final String EMPTY_DATE_SEARCH_RESULT_MESSAGE = "No deadlines or events found for this date!\n";
}
