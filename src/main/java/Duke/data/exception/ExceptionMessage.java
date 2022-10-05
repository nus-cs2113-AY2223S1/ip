package Duke.data.exception;

public class ExceptionMessage {
    public static final String EMPTY_HANDLER = "The list is empty and there is nothing to print :(";
    public static final String EMPTY_MARK_INDEX = "No index given to mark/unmark:(\nPlease key in a valid integer :)";
    public static final String INPUT_INDEX_OUT_OF_BOUNDS = "There is no task with index ";
    public static final String DEADLINE_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters.\n" +
            "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
            "and lastly followed by the due date in the form yyyy-mm-dd :)");
    public static final String EVENT_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters. \n" +
            "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
            "lastly followed by the event date and time in the form yyyy-mm-dd:)");
    public static final String TODO_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters.\n" +
            " Please enter \"todo\", followed by the task :)");
    public static final String UNKNOWN_INPUTS = "Sorry I do not understand your command :(";
    public static final String FILE_ERROR = "File does not exist or Path is wrong :(";
    public static final String LOAD_FILE_ERROR = "Unable to load file. File does not exist or Path is wrong :(\n" +
            "If you have a load file, please check that the file is in the correct directory. Otherwise, ignore this.\n";
    public static final String UNKNOWN_INPUTS_LOAD = "There is an unknown command in your load file :(\n " +
            "Please check that you have uploaded the correct file";
    public static final String DATE_PARSE_ERROR = "The date specified is wrong.\nPlease give your date in the form of yyyy-mm-dd";
    public static final String EMPTY_DELETE_INDEX = "No index given to delete:(\nPlease key in a valid integer :)";
}
