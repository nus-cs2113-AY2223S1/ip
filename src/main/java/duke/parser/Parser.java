package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkOrUnmarkCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.errorhandling.DukeException;

public class Parser {
    protected static final int COMMAND_INDEX = 0;
    protected static final int TASK_DETAIL_INDEX = 1;
    protected static final boolean RESET = false;
    protected static boolean isToDo = false;
    protected static boolean isDeadline = false;
    protected static boolean isEvent = false;
    protected static boolean isMarkOrUnmark = false;
    protected static boolean isValidMarkOrUnmark = false;
    protected static boolean isDelete = false;
    protected static boolean isValidDelete = false;
    protected static boolean isMark = false;
    protected static boolean isBye = false;
    protected static boolean isList = false;
    protected static boolean isAdd = false;
    protected static boolean isValidAdd = false;

    /**
     * Checks all the type of commands to classify them.
     *
     * @param splitCommand is a list of words that are separated by space.
     */
    public static void checkCommandType(String[] splitCommand) {
        isBye = splitCommand[COMMAND_INDEX].equals("bye");
        isList = splitCommand[COMMAND_INDEX].equals("list");
        isToDo = splitCommand[COMMAND_INDEX].equals("todo");
        isDeadline = splitCommand[COMMAND_INDEX].equals("deadline");
        isEvent = splitCommand[COMMAND_INDEX].equals("event");
        isMark = splitCommand[COMMAND_INDEX].equals("mark");
        isDelete = splitCommand[COMMAND_INDEX].equals("delete");
        isMarkOrUnmark = splitCommand[COMMAND_INDEX].equals("mark")
                || splitCommand[COMMAND_INDEX].equals("unmark");
        isAdd = isToDo || isEvent || isDeadline;
    }

    /**
     * Verifies the command detail in so to proof that the type of command is valid.
     *
     * @param isBlankDetail which checks if the TASK_DETAIL_INDEX is empty or not.
     */
    public static void validCommandDetail(boolean isBlankDetail) {
        if (!isBlankDetail) {
            isValidAdd = isAdd;
            isValidDelete = isDelete;
            isValidMarkOrUnmark = isMarkOrUnmark;
        } else {
            isValidAdd = RESET;
            isValidDelete = RESET;
            isValidMarkOrUnmark = RESET;
        }
    }

    public static void checkCommandDetail(String[] splitCommand) {
        boolean isTaskCommand = splitCommand.length > 1;
        validateCommandDetail(splitCommand, isTaskCommand);
    }

    /**
     * Verifies the command detail, to ensure that the TASK_DETAIL_INDEX has input.
     *
     * @param splitCommand is a list of words that are separated by space.
     * @param isTaskCommand which check if the command as a task detail.
     */
    public static void validateCommandDetail(String[] splitCommand, boolean isTaskCommand) {
        boolean isBlankDetail = true;
        if (isTaskCommand) {
            isBlankDetail = splitCommand[TASK_DETAIL_INDEX].isBlank();

        }
        validCommandDetail(isBlankDetail);
    }

    /**
     * Parse which deals with making sense of the user command.
     *
     * @param fullCommand which is the command that the user input.
     * @return c
     * @throws DukeException which display an error if there is an invalid command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command;
        String[] splitCommand = fullCommand.split(" ", 2);
        checkCommandType(splitCommand);
        checkCommandDetail(splitCommand);
        command = initializeTypeOfCommand(splitCommand);
        return command;
    }

    /**
     * Initialises the type of command base on what the user input. Else, presents
     * an error if the type of command is not valid.
     *
     * @param splitCommand is a list of words that are separated by space.
     * @return command based on the type of command to initialise type of Command class.
     * @throws DukeException An error that will be generated if the command is not valid.
     */
    public static Command initializeTypeOfCommand(String[] splitCommand) throws DukeException {
        Command command;
        if (isBye) {
            command = new ExitCommand();
        } else if (isList) {
            command = new ListCommand();
        } else if (isValidAdd) {
            command = new AddCommand(splitCommand);
        } else if (isValidDelete) {
            command = new DeleteCommand(splitCommand);
        } else if (isValidMarkOrUnmark) {
            command = new MarkOrUnmarkCommand(splitCommand);
        } else {
            String error = showErrorMessage();
            throw new DukeException(error);
        }
        return command;
    }

    public static String showErrorMessage() {
        String error = "\t ☹ HMM?? I'm sorry, but I don't know what that means :-(";
        if (isAdd || isDelete || isMarkOrUnmark) {
            error = showCommandError();
        }
        return error;
    }

    /**
     * Shows the type of command Error based on what the user input.
     *
     * @return error that shows the error message based on the type of command.
     */
    public static String showCommandError() {
        String error = null;
        if (isToDo) {
            error = "\t ☹ OH MAN!!! The description of a todo cannot be empty.";
        } else if (isDeadline) {
            error = "\t ☹ OH MAN!!! The description of a deadline cannot be empty.";
        } else if (isEvent) {
            error = "\t ☹ OH MAN!!! The description of an event cannot be empty.";
        } else if (isMarkOrUnmark) {
            error = "\t ☹ OH MAN!!! You did not tell me which to (un)mark.";
        } else if (isDelete) {
            error = "\t ☹ OH MAN!!! You did not tell me what to delete.";
        }
        return error;
    }
}
