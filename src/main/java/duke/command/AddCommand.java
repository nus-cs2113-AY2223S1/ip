package duke.command;

import duke.Duke;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.DukeDateTimeParser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

public abstract class AddCommand extends Command {
    public static String COMMAND_WORD;
    protected String taskName;

    public AddCommand() {
        super();
    }

    public abstract void checkAndSetParameters(String parameterInput) throws DukeException;

    protected abstract void checkParameters(String parameterInput) throws DukeException;

    protected abstract void setParameters(String parameterInput) throws DukeException;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

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

    protected boolean containsTaskTime(String parameterInput, String separator) {
        // Trim description from parameterInput
        String[] splits = parameterInput.split(separator, 2);
        String time = splits[1];

        // Check if description is empty or only contain whitespaces
        time = time.replaceAll("\\s+", "");
        if (time.equals("")) {
            return false;
        } else {
            return true;
        }
    }

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

    protected static String[] splitTaskName(String regex, String parameterInput) {
        String[] splits = parameterInput.split(regex, 2);
        if (splits.length == 1) {
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
