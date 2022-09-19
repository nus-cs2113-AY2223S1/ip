package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
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
        if (!separatorIsEmpty && startFromTimeSeparator(parameterInput, separator)) {
            return false;
        }

        // Trim description from parameterInput
        String[] splits = parameterInput.split(separator, 2);
        String description = splits[0];

        // Check if description is empty or only contain whitespaces
        description = description.replaceAll("\\s+", "");
        if (description.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the user's input contains a time after its separator.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and time, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput contains a time.
     */
    protected boolean containsTaskTime(String parameterInput, String separator) {
        // Trim time from parameterInput
        String[] splits = parameterInput.split(separator, 2);
        String time = splits[1];

        // Check if time is empty or only contain whitespaces
        time = time.replaceAll("\\s+", "");
        if (time.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the user's input contains a separator.
     *
     * @param parameterInput The extracted part of user input after the command entered.
     * @param separator      The separator used to separate description and time, such as /by or /at.
     * @return A boolean value to indicate if the parameterInput contains the given time separator.
     */
    protected boolean containsTimeSeparator(String parameterInput, String separator) {
        if (startFromTimeSeparator(parameterInput, separator)) {
            return true;
        }
        if (!parameterInput.contains(separator)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean startFromTimeSeparator(String parameterInput, String separator) {
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
    protected static String[] splitTaskName(String regex, String parameterInput) {
        // Split the user input into [task description, task time]
        String[] splits = parameterInput.split(regex, 2);
        if (splits.length == 1) {
            // Populate an empty string into the array when the size is only 1 for code security
            return new String[]{splits[0], ""};
        }
        return splits;
    }

    protected LocalDateTime convertStringToLocalDateTime(String parameterInput) throws DukeException {
        DukeDateTimeParser parser = new DukeDateTimeParser();
        LocalDateTime dateTimeInput = parser.parse(parameterInput);
        return dateTimeInput;
    }
}
