package duke;

import java.io.IOException;

/**
 * Represents the primary command passed in by user
 * Eg. mark, unmark, delete, find
 */
public class Command {
    private static String command;
    public Command(String command) {
        this.command = command;
    }

    public static String getCommand() {
        return command;
    }

    public static boolean isExit() {
        if (command.equals("bye")) {
            return true;
        }
        return false;
    }

    public static void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws DukeException, IOException{
        String command = getCommand();
        int taskID;
        int taskCounter = tasks.getTaskCounter();
        switch (command) {
        case "bye":
            break;
        case "list":
            ui.showList(tasks, taskCounter);
            break;
        case "mark":
            taskID = Parser.getTaskID(fullCommand);
            tasks.markTask(taskID);
            ui.showMarked(tasks, taskID);
            break;
        case "unmark":
            taskID = Parser.getTaskID(fullCommand);
            tasks.unmarkTask(taskID);
            ui.showUnmarked(tasks, taskID);
            break;
        case "delete":
            taskID = Parser.getTaskID(fullCommand);
            ui.showDeleted(tasks, taskID);
            tasks.deleteTask(taskID);
            break;
        case "todo":
        case "deadline":
        case "event":
            tasks.createTask(fullCommand);
            break;
        default:
            ui.showInvalidInputError();
        }
        storage.saveFile(storage.getFilePath(), Parser.convertTaskListIntoFileContent(tasks, tasks.getTaskCounter()));
    }
}
