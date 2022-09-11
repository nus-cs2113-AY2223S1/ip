package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    //Zhou Zhou


    public static void main(String[] args) {
        UI console = new UI();
        TaskManager taskManager = new TaskManager();
        try {
            FileAccess.loadTasks();
            UI.respond(Command.LOAD);
            UI.respond(Command.LIST);
        } catch (FileNotFoundException e) {
            try {
                FileAccess.createDataFile();
                UI.respond(Command.CREATE_DATA_FILE);
            } catch (IOException ex) {
                UI.respond(new DukeException(ExceptionType.MISSING_DATA_FILE));
                return;
            }
        } catch (DukeException e) {
            UI.respond(e);
            return;
        }
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
                    TaskManager.markAsDone(Integer.parseInt(arguments.trim()));
                    UI.respond(Command.MARK, TaskManager.Tasks[Integer.parseInt(arguments.trim())].listTask());
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "unmark":
                try {
                    TaskManager.markAsNotDone(Integer.parseInt(arguments.trim()));
                    UI.respond(Command.UNMARK, TaskManager.Tasks[Integer.parseInt(arguments.trim())].listTask());
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "todo":
                try {
                    TaskManager.addTask(Command.TODO, arguments.trim());
                    UI.respond(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "event":
                try {
                    TaskManager.addTask(Command.EVENT, arguments.trim());
                    UI.respond(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
                } catch (DukeException e) {
                    UI.respond(e);
                }
                break;
            case "deadline":
                try {
                    TaskManager.addTask(Command.DEADLINE, arguments.trim());
                    UI.respond(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
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
        if (TaskManager.getTasksCount() > 0) {
            try {
                FileAccess.saveTasks();
                UI.respond(Command.SAVE);
            } catch (IOException e) {
                UI.respond(new DukeException(ExceptionType.MISSING_DATA_FILE));
                UI.output(e.getMessage());
            }
        }
    }
}
