package duke;

import duke.command.Command;
import duke.command.CommandMenu;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

import java.util.ArrayList;

public abstract class Parser {
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

    private static String getTaskType(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[0];
    }

    private static String getTaskStatus(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[1];
    }

    private static String getTaskParameters(String line, String separator, int limit, int index)
            throws ArrayIndexOutOfBoundsException {
        return line.split(separator, limit)[index].trim();
    }

    private static String getTaskDescription(String taskParameters, String separator)
            throws ArrayIndexOutOfBoundsException {
        return taskParameters.split(separator, 2)[0].trim();
    }

    private static String getTaskDatetime(String taskParameters, String separator)
            throws ArrayIndexOutOfBoundsException {
        return taskParameters.split(separator, 2)[1].trim();
    }

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
        case CommandMenu.FIND_COMMAND:
            try {
                taskDescription = getTaskParameters(fullCommand, " ", 2, 1);
                command = new Command(commandKeyword, taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingSearchWordErrorMessage();
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

    private static int getTaskNumber(String fullCommand) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String taskNumberInput = fullCommand.split(" ", 2)[1];
        int taskNumber = Integer.parseInt(taskNumberInput);
        return taskNumber;
    }
}
