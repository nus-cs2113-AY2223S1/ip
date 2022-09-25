package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.parser.DukeDateTimeParser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * <code>AddCommand</code> is the abstract base class for the commands that add tasks to task list.
 * <p>
 * Extended by <code>TodoCommand</code>, <code>DeadlineCommand</code> and <code>EventCommand</code>.
 */
public abstract class AddCommand extends Command {
    public static String COMMAND_WORD;
    protected String taskName;

    /**
     * Constructor for <code>AddCommand</code>
     */
    public AddCommand() {
        super();
    }

    public abstract void checkAndSetParameters(String parameterInput) throws DukeException;

    protected abstract void checkParameters(String parameterInput) throws DukeException;

    protected abstract void setParameters(String parameterInput) throws DukeException;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if the user's input contains a task description.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and other parameter, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput contains a task description
     */
    protected boolean containsTaskDescription(String parameterInput, String separator) {
        boolean separatorIsEmpty = separator.isEmpty();
        boolean hasDescription = false;
        if (!separatorIsEmpty && isStartedFromTimeSeparator(parameterInput, separator)) {
            return hasDescription;
        }

        // Trim description from parameterInput
        String[] splits = parameterInput.split(separator, 2);
        String description = splits[0];

        // Check if description is empty or only contain whitespaces
        description = description.replaceAll("\\s+", "");
        hasDescription = !description.equals("");

        return hasDescription;
    }

    /**
     * Check if the user's input contains a time after its separator.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and time, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput contains a time.
     */
    protected boolean containsTaskTime(String parameterInput, String separator) {
        boolean hasTime = false;
        // Trim time from parameterInput
        String[] splits = parameterInput.split(separator, 2);
        String time = splits[1];

        // Check if time is empty or only contain whitespaces
        time = time.replaceAll("\\s+", "");
        hasTime = !time.equals("");

        return hasTime;
    }

    /**
     * Check if the user's input contains a separator.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and time, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput contains the given time separator.
     */
    protected boolean containsTimeSeparator(String parameterInput, String separator) {
        boolean startsFromTimeSeparator;
        boolean hasTimeSeparator;

        startsFromTimeSeparator = isStartedFromTimeSeparator(parameterInput, separator);
        hasTimeSeparator = parameterInput.contains(separator);

        return startsFromTimeSeparator || hasTimeSeparator;
    }

    /**
     * Check if the user's input starts from a separator.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and time, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput starts with given time separator.
     */
    private boolean isStartedFromTimeSeparator(String parameterInput, String separator) {
        if (parameterInput.startsWith(separator.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <ui>
     * Split the user input into two parts.
     * <li>First part contains the task description.</li>
     * <li>Second part contains the task time, if there's any.</li>
     * </ui>
     *
     * @param regex          The regular expression used to split the user's input
     * @param parameterInput The extracted part of user input after the command entered.
     * @return A string array of size 2 containing the task description and task time.
     */
    protected static String[] splitTaskName(String regex, String parameterInput) throws DukeException {
        // Split the user input into [task description, task time]
        String[] splits = parameterInput.split(regex, 2);

        // Triggers format error when task time cannot be found
        if (splits.length == 1) {
            throw new InvalidDateTimeFormatException();
        }

        return splits;
    }

    /**
     * Parse the datetime in String extracted from the user input into a LocalDateTime object
     *
     * @param parameterInput A string containing the datetime of the task
     * @return A parsed LocalDateTime object from the user input
     * @throws DukeException Exception triggered on invalid LocalDateTime format.
     */
    protected LocalDateTime convertStringToLocalDateTime(String parameterInput) throws DukeException {
        DukeDateTimeParser parser = new DukeDateTimeParser();
        LocalDateTime dateTimeInput = parser.parse(parameterInput);
        return dateTimeInput;
    }
}
