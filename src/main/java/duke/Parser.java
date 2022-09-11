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

    public static boolean isOnline(String command, TaskManager taskManager) {
        String keyword = extractKeyword(command);
        switch(keyword) {
        case INVALID_BASIC_COMMAND:
            System.out.println(Printables.invalidBasicCommandMessage);
            break;
        case COMMAND_BYE:
            return false;
        case COMMAND_LIST:
            taskManager.listTasks();
            break;
        case COMMAND_HELP:
            System.out.println(Printables.helpManual);
            break;
        case COMMAND_TODOS:
            try {
                CommandTodo.processNewTodo(command, taskManager);
            } catch (DukeException.IllegalTodoException e) {
                System.out.println("Please enter a name for the todo task!");
            }
            break;
        case COMMAND_DEADLINES:
            try {
                CommandDeadline.processNewDeadline(command, taskManager);
            } catch (DukeException.IllegalDeadlineFormatException e) {
                System.out.println("Please enter a deadline task followed by a '/' and indicate a deadline");
            } catch (DukeException.IllegalDeadlineDateException e) {
                System.out.println("Please enter a deadline to complete the task!");
            }
            break;
        case COMMAND_EVENTS:
            try {
                CommandEvent.processNewEvent(command, taskManager);
            } catch (DukeException.IllegalEventFormatException e) {
                System.out.println("Please enter an event task followed by a '/' and indicate when");
            } catch (DukeException.IllegalEventDateException e) {
                System.out.println("Please enter when this event is happening!");
            }
            break;
        case COMMAND_MARK:
            try {
                CommandMarking.processMarking(command, taskManager, true);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                System.out.println("Please enter a task number only to be marked");
            }
            break;
        case COMMAND_UNMARK:
            try {
                CommandMarking.processMarking(command, taskManager, false);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                System.out.println("Please enter a task number only to be unmarked");
            }
            break;
        case COMMAND_DELETE:
            try {
                CommandDelete.processDelete(command, taskManager);
            } catch (DukeException.IllegalDeleteIndexException e) {
                System.out.println("Please enter a task number only to be deleted");
            }
            break;
        default:
            System.out.println("Invalid command");
            break;
        }

        return true;
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