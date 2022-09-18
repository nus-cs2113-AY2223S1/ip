package duke;

import duke.commands.*;

public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_HELP = "help";
    private static final String INVALID_BASIC_COMMAND = "invalidBasicCommand";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODOS = "todo";
    private static final String COMMAND_DEADLINES = "deadline";
    private static final String COMMAND_EVENTS = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_CHECKOUT = "checkout";
    private static final String QUIT_FLAG = "quit";

    public static String runCommand(String command, TaskList taskList) {
        String keyword = extractKeyword(command);
        String response = null;

        switch(keyword) {
        case INVALID_BASIC_COMMAND:
            response =  Printables.INVALID_BASIC_COMMAND_MESSAGE;
            break;
        case COMMAND_BYE:
            response = QUIT_FLAG;
            break;
        case COMMAND_LIST:
            response = taskList.listTasks();
            break;
        case COMMAND_HELP:
            response = Printables.HELP_MANUAL;
            break;
        case COMMAND_TODOS:
            try {
                response = CommandTodo.processNewTodo(command, taskList);
            } catch (DukeException.IllegalTodoException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_DEADLINES:
            try {
                response = CommandDeadline.processNewDeadline(command, taskList);
            } catch (DukeException.IllegalDeadlineFormatException e) {
                response = e.getMessage();
            } catch (DukeException.IllegalDeadlineDateException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_EVENTS:
            try {
                response = CommandEvent.processNewEvent(command, taskList);
            } catch (DukeException.IllegalEventFormatException e) {
                response = e.getMessage();
            } catch (DukeException.IllegalEventDateException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_MARK:
            try {
                response = CommandMarking.processMarking(command, taskList, true);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_UNMARK:
            try {
                response = CommandMarking.processMarking(command, taskList, false);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_DELETE:
            try {
                response = CommandDelete.processDelete(command, taskList);
            } catch (DukeException.IllegalDeleteIndexException e) {
                response = e.getMessage();
            }
            break;
        case COMMAND_CHECKOUT:
            try {
                response = CommandCheckout.processCheckout(command, taskList);
            } catch (DukeException.IllegalDateTimeException e) {
                response = e.getMessage();
            }
            break;
        default:
            response = Printables.INVALID_GENERAL_COMMAND;
            break;
        }

        return response;
    }

    private static String extractKeyword(String command) {
        String[] arrOfCommand = command.split(" ");
        if ((arrOfCommand[0].equals(COMMAND_BYE)
                || arrOfCommand[0].equals(COMMAND_LIST)
                || arrOfCommand[0].equals(COMMAND_HELP)) && arrOfCommand.length > 1) {
            return INVALID_BASIC_COMMAND;
        }
        return arrOfCommand[0];
    }
}