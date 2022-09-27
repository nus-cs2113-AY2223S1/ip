package duke;

import duke.command.Command;
import duke.command.CommandMenu;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

import java.util.ArrayList;

/**
 * Represents a parser utility that extract parameters from a line of string.
 * It is mainly used for parsing commands inputted by the user and the data in the data file.
 */
public abstract class Parser {
    /**
     * Returns the list of tasks from the strings stored in the text file.
     * It parses the tasks line by line and extract parameters according to the task type the task belongs to.
     *
     * @param taskDataItems A list of strings where each string is a line in the data text file that represent 1 task
     *                      each.
     * @return List of <code>Task</code> objects parsed from the list of strings.
     */
    public static ArrayList<Task> parseTaskData(ArrayList<String> taskDataItems) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : taskDataItems) {
            String taskType = getTaskType(line);
            String taskStatus = getTaskStatus(line);
            String taskParameters = getTaskParameters(line, TaskList.FILE_STRING_SEPARATOR, 3, 2);
            String taskDescription = getTaskDescription(taskParameters, TaskList.FILE_STRING_SEPARATOR);
            String taskDatetime = "";

            switch (taskType) {
            case Todo.ICON:
                Todo todoTask = new Todo(taskDescription, taskStatus);
                tasks.add(todoTask);
                break;
            case Event.ICON:
                taskDatetime = getTaskDatetime(taskParameters, TaskList.FILE_STRING_SEPARATOR);
                Event event = new Event(taskDescription, taskDatetime, taskStatus);
                tasks.add(event);
                break;
            case Deadline.ICON:
                taskDatetime = getTaskDatetime(taskParameters, TaskList.FILE_STRING_SEPARATOR);
                Deadline deadline = new Deadline(taskDescription, taskDatetime, taskStatus);
                tasks.add(deadline);
                break;
            default:
                // Skip the line when it is an undefined task
                break;
            }
        }

        return tasks;
    }

    /**
     * Returns the task type from the line representing a task.
     * @param line A line with task details separated by <code>TaskList.FILE_STRING_SEPARATOR</code>.
     * @return Task type
     */
    private static String getTaskType(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[0];
    }

    /**
     * Returns the task status from the line representing a task.
     * @param line A line with task details separated by <code>TaskList.FILE_STRING_SEPARATOR</code>.
     * @return Task status that represent whether a task is done.
     */
    private static String getTaskStatus(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[1];
    }

    /**
     * Returns the string that include the possible parameters that are used to define a task.
     * @param line A line with task details separated by a string separator.
     * @param separator String that separate task details in the line.
     * @param limit Number of times to split the line.
     * @param index Index of the task parameters string in the array of strings after being split.
     *              In other words, the number of strings split before the task parameters.
     * @return String that include the possible parameters that are used to define a task.
     * @throws ArrayIndexOutOfBoundsException If the task parameters are not specified after the command keyword.
     */
    private static String getTaskParameters(String line, String separator, int limit, int index)
            throws ArrayIndexOutOfBoundsException {
        return line.split(separator, limit)[index].trim();
    }

    /**
     * Return the task description as a string, extracted from the string of task parameters, potentially separated by
     * the specified separator.
     * @param taskParameters A string containing the task parameters. If there are more than 1 parameters, then the
     *                       string includes the specified separator.
     * @param separator Separator represented by a string which separates multiple task parameters.
     * @return Task description.
     */
    private static String getTaskDescription(String taskParameters, String separator) {
        return taskParameters.split(separator, 2)[0].trim();
    }

    /**
     * Return the task datetime as a string, extracted from the string of task parameters, separated by the specific
     * separator. Only event and deadline tasks require task datetime.
     * @param taskParameters A string containing the task parameters which includes the specified separator.
     * @param separator Separator represented by a string which separates multiple task parameters.
     * @return Task datetime. Only event and deadline tasks have date and time provided.
     * @throws ArrayIndexOutOfBoundsException If the task parameters did not provide both the task description and task
     *                                        datetime.
     */
    private static String getTaskDatetime(String taskParameters, String separator)
            throws ArrayIndexOutOfBoundsException {
        return taskParameters.split(separator, 2)[1].trim();
    }

    /**
     * Returns the command as a <code>Command</code> object specified by the line of command.
     * @param fullCommand Complete line of command to be parsed.
     * @param ui User interface.
     * @return <code>Command</code> object with the parsed parameters.
     */
    public static Command parseCommand(String fullCommand, Ui ui) {
        String commandKeyword = fullCommand.split(" ", 2)[0];
        Command command = new Command();
        String taskParameters;
        String taskDescription;
        String taskDatetime;

        switch (commandKeyword){
        case CommandMenu.HELP_COMMAND:
        case CommandMenu.LIST_COMMAND:
        case CommandMenu.EXIT_COMMAND:
        case "":
            command = new Command(commandKeyword);
            break;
        case CommandMenu.MARK_COMMAND:
        case CommandMenu.UNMARK_COMMAND:
        case CommandMenu.DELETE_COMMAND:
            try {
                int taskNumber = getTaskNumber(fullCommand);
                command = new Command(commandKeyword, taskNumber);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingTaskNumberErrorMessage();
                ui.showCommandSyntaxHint(commandKeyword);
            } catch (NumberFormatException e) {
                ui.showTaskNumberFormatErrorMessage();
                ui.showCommandSyntaxHint(commandKeyword);
            }
            break;
        case CommandMenu.ADD_TODO_COMMAND:
            try {
                taskDescription = getTaskParameters(fullCommand, " ", 2, 1);
                command = new Command(commandKeyword, taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showAddTodoErrorMessage();
                ui.showCommandSyntaxHint(commandKeyword);
            }
            break;
        case CommandMenu.ADD_DEADLINE_COMMAND:
            try {
                taskParameters = getTaskParameters(fullCommand, " ", 2, 1);
                taskDescription = getTaskDescription(taskParameters, Deadline.SEPARATOR);
                taskDatetime = getTaskDatetime(taskParameters, Deadline.SEPARATOR);
                command = new Command(commandKeyword, taskDescription, taskDatetime);
            } catch (IndexOutOfBoundsException e) {
                ui.showAddDeadlineErrorMessage();
                ui.showCommandSyntaxHint(commandKeyword);
            }
            break;
        case CommandMenu.ADD_EVENT_COMMAND:
            try {
                taskParameters = getTaskParameters(fullCommand, " ", 2, 1);
                taskDescription = getTaskDescription(taskParameters, Event.SEPARATOR);
                taskDatetime = getTaskDatetime(taskParameters, Event.SEPARATOR);
                command = new Command(commandKeyword, taskDescription, taskDatetime);
            } catch (IndexOutOfBoundsException e) {
                ui.showAddEventErrorMessage();
                ui.showCommandSyntaxHint(commandKeyword);
            }
            break;
        default:
            command = new Command(CommandMenu.INVALID_COMMAND);
            break;
        }

        return command;
    }

    /**
     * Return the task number of the specified in the command.
     * @param fullCommand Complete line of command to be parsed.
     * @return Task number shown on the list.
     * @throws ArrayIndexOutOfBoundsException If the command does not specify the task number.
     * @throws NumberFormatException If the command specifies a non-integer as the number.
     */
    private static int getTaskNumber(String fullCommand) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String taskNumberInput = fullCommand.split(" ", 2)[1];
        int taskNumber = Integer.parseInt(taskNumberInput);
        return taskNumber;
    }
}
