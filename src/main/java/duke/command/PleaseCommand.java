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
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.outputWithLines("Slave Kai is glad to help! \n" +
                "Available commands: \n" +
                "help - to try asking for help\n" +
                "please - to actually get help\n" +
                "list - to list all tasks\n" +
                "mark {task number} - to mark that task as done\n" +
                "unmark {task number} - to mark that task as not done\n" +
                "todo {description} - to add a new todo task\n" +
                "event {description} /{event time} - to add a new event task\n" +
                "deadline {description} /{deadline time} - to add a new deadline task\n" +
                "bye - to exit");
    }
}
