package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command for displaying help message
 */
public class PleaseCommand extends Command {

    public PleaseCommand() {
    }

    /**
     * Displays available commands to user
     *
     * @param taskList ArrayList containing current tasks
     * @param ui       Ui object for communicating with user
     * @param storage  Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output("Slave Kai is glad to help! \n" +
                "Available commands: \n" +
                "bye - to exit Duke\n" +
                "help - to try asking for help\n" +
                "please - to actually get help\n" +
                "todo {description} - to add a new todo\n" +
                "event {description} /{date} {time (optional)} - to add a new event\n" +
                "deadline {description} /{date} {time (optional)} - to add a new deadline\n" +
                "mark/unmark {task number} - to mark a task as done/not done\n" +
                "delete {task number} - to delete a task\n" +
                "list - to list all tasks\n" +
                "find {keyword} - to list tasks containing {keyword}\n" +
                "done/undone - to list tasks that are marked as done/not done\n" +
                "on/before/after {date} - to list tasks on/before/after {date}\n" +
                "\n" +
                "Note: all dates must be entered in the format dd-mm-yyyy, eg. 22-7-2026\n" +
                "and all times must be entered in the format hhmm, eg. 2359");
    }
}
