package duke;

import java.io.IOException;

/**
 * Represents the primary command passed in by user
 * Eg. mark, unmark, delete, find
 */
public class Command {
    private static String command;
    public Command(String command) {
        Command.command = command;
    }

    public static String getCommand() {
        return command;
    }

    public boolean isExit() {
        return command.equals("bye");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws DukeException, IOException{
        String command = getCommand();
        int taskID;
        String keyword;
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
            if (taskID <= tasks.getTaskCounter())
                ui.showDeleted(tasks, taskID);
            tasks.deleteTask(taskID);
            break;
        case "find":
            keyword = Parser.getKeyword(fullCommand);
            ui.showSearched(tasks, keyword);
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            tasks.createTask(fullCommand);
            break;
        default:
            ui.showInvalidInputError();
        }
        storage.saveFile(storage.getFilePath(), Parser.convertTaskListIntoFileContent(tasks, tasks.getTaskCounter()));
    }
}
