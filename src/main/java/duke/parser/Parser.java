package duke.parser;

import duke.command.*;
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
    protected static boolean isFind = false;
    protected static boolean isValidFind = false;
    protected static boolean isValidAdd = false;

    public static void checkCommandType(String[] splitCommand) {
        isBye = splitCommand[COMMAND_INDEX].equals("bye");
        isList = splitCommand[COMMAND_INDEX].equals("list");
        isFind = splitCommand[COMMAND_INDEX].equals("find");
        isToDo = splitCommand[COMMAND_INDEX].equals("todo");
        isDeadline = splitCommand[COMMAND_INDEX].equals("deadline");
        isEvent = splitCommand[COMMAND_INDEX].equals("event");
        isMark = splitCommand[COMMAND_INDEX].equals("mark");
        isDelete = splitCommand[COMMAND_INDEX].equals("delete");
        isMarkOrUnmark = splitCommand[COMMAND_INDEX].equals("mark")
                || splitCommand[COMMAND_INDEX].equals("unmark");
        isAdd = isToDo || isEvent || isDeadline;
    }

    private static void validCommandDetail(boolean isBlankDetail) {
        if (!isBlankDetail) {
            isValidAdd = isAdd;
            isValidDelete = isDelete;
            isValidMarkOrUnmark = isMarkOrUnmark;
            isValidFind = isFind;
        } else {
            isValidAdd = RESET;
            isValidDelete = RESET;
            isValidMarkOrUnmark = RESET;
            isValidFind = RESET;
        }
    }

    public static void checkCommandDetail(String[] splitCommand) {
        boolean isTaskCommand = splitCommand.length > 1;
        validateCommandDetail(splitCommand, isTaskCommand);
    }

    public static void validateCommandDetail(String[] splitCommand, boolean isTaskCommand) {
        boolean isBlankDetail = true;
        if (isTaskCommand) {
            isBlankDetail = splitCommand[TASK_DETAIL_INDEX].isBlank();

        }
        validCommandDetail(isBlankDetail);
    }

    public static Command parse(String fullCommand) throws DukeException {
        Command c;
        String[] splitCommand = fullCommand.split(" ", 2);
        checkCommandType(splitCommand);
        checkCommandDetail(splitCommand);
        c = initializeTypeOfCommand(splitCommand);
        return c;
    }

    public static Command initializeTypeOfCommand(String[] splitCommand) throws DukeException {
        Command c;
        if (isBye) {
            c = new ExitCommand();
        } else if (isList) {
            c = new ListCommand();
        } else if (isValidAdd) {
            c = new AddCommand(splitCommand);
        } else if (isValidDelete) {
            c = new DeleteCommand(splitCommand);
        } else if (isValidMarkOrUnmark) {
            c = new MarkOrUnmarkCommand(splitCommand);
        } else if (isValidFind) {
            c = new FindCommand(splitCommand);
        } else {
            String error = errorMessage();
            throw new DukeException(error);
        }
        return c;
    }

    public static String errorMessage() {
        String error = "\t ☹ HMM?? I'm sorry, but I don't know what that means :-(";
        if (isAdd || isDelete || isMarkOrUnmark || isFind) {
            error = taskError();
        }
        return error;
    }

    public static String taskError() {
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
        } else if (isFind) {
            error = "\t ☹ OH MAN!!! You did not tell me what to find.";
        }
        return error;
    }
}
