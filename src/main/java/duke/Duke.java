package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskManager;

public class Duke {

    //Zhou Zhou


    public static void main(String[] args) {
        UI console = new UI();
        TaskManager taskManager = new TaskManager();
        String input = UI.input();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String arguments = input.substring(words[0].length());
            switch (words[0]) {
            case "hi":
                UI.respond(Command.HI);
                break;
            case "help":
                UI.respond(Command.HELP);
                break;
            case "please":
                UI.respond(Command.PLEASE);
                break;
            case "list":
                UI.respond(Command.LIST);

                break;
            case "mark":
                try {
                    String task = taskManager.markAsDone(Integer.parseInt(arguments.trim()));
                    UI.respond(Command.MARK, task);
                } catch (DukeException e) {
                    UI.respond(e);
                } catch (NumberFormatException e) {
                    UI.respond(new DukeException(ExceptionType.INVALID_TASK_NUMBER));
                }
                break;
            case "unmark":
                try {
                    String task = taskManager.markAsNotDone(Integer.parseInt(arguments.trim()));
                    UI.respond(Command.UNMARK, task);
                } catch (DukeException e) {
                    UI.respond(e);
                } catch (NumberFormatException e) {
                    UI.respond(new DukeException(ExceptionType.INVALID_TASK_NUMBER));
                }
                break;
            case "delete":
                try {
                    String task = taskManager.deleteTask(Integer.parseInt(arguments.trim()));
                    UI.respond(Command.DELETE, task);
                } catch (DukeException e) {
                    UI.respond(e);
                } catch (NumberFormatException e) {
                    UI.respond(new DukeException(ExceptionType.INVALID_TASK_NUMBER));
                }
                break;
            case "todo":
                try {
                    String task = taskManager.addTask(Command.TODO, arguments.trim());
                    UI.respond(Command.ADD, task);
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "event":
                try {
                    String task = taskManager.addTask(Command.EVENT, arguments.trim());
                    UI.respond(Command.ADD, task);
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "deadline":
                try {
                    String task = taskManager.addTask(Command.DEADLINE, arguments.trim());
                    UI.respond(Command.ADD, task);
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            default:
                UI.respond(Command.INVALID);
                break;
            }
            input = UI.input();
        }
        UI.respond(Command.BYE);
    }
}
