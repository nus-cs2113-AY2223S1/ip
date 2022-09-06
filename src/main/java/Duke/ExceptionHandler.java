package Duke;

public class ExceptionHandler {
    public static final String EMPTY_HANDLER = "The list is empty and there is nothing to print :(";
    public static final String EMPTY_MARK_INDEX = "No index given to mark";
    public static final String MARK_INDEX_OUT_OF_BOUNDS = "There is no task with index ";
    public static final String DEADLINE_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters.\n" +
            "Please enter \"deadline\", followed by the task, followed by \"/by\", \n" +
            "and lastly followed by the due date :)");
    public static final String EVENT_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters. \n" +
            "Please enter \"event\", followed by the event, followed by \"/at\", and \n" +
            "lastly followed by the event date and time:)");
    public static final String TODO_INPUT_ERROR = ("Sorry :(\n I don't think you have properly keyed in all the parameters.\n" +
            " Please enter \"todo\", followed by the task :)");
    public static final String UNKNOWN_INPUTS = "Sorry I do not understand your command :(";
}
