package duke.parser;


import duke.command.*;
import duke.tasks.TaskList;

public class Parser {
    /**
     * Commands
     */
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String FIND = "find";

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String START_LINE_DELIMITER = "^";

    public Parser() {
    }

    /**
     * Parses input and returns a command to be executed
     *
     * @param taskList taskList to execute command on
     * @param input Input from the user to extract command from
     * @return Command to be executed
     */
    public static Command parsedCommand(TaskList taskList, String input) {

        final String[] splitLine = input.split(" ", 2);
        final String command = splitLine[0];

        final String commandArguments = input.replaceAll(START_LINE_DELIMITER + command + SPACE_DELIMITER, "");

        if (command.equals(LIST)) {
            return new ListCommand(taskList, commandArguments);
        }

        if (command.equals(TODO)) {
            return new AddTodoCommand(taskList, commandArguments);
        }

        if (command.equals(EVENT)) {
            return new AddEventCommand(taskList, commandArguments);
        }
        if (command.equals(DEADLINE)) {
            return new AddDeadlineCommand(taskList, commandArguments);
        }
        if (command.equals(DELETE)) {
            return new DeleteCommand(taskList, commandArguments);
        }
        if (command.equals(DONE)) {
            return new MarkAsDoneCommand(taskList, commandArguments);
        }
        if (command.equals(FIND)) {
            return new FindCommand(taskList, commandArguments);
        }
        return new DoNothingCommand(taskList);

    }
}
