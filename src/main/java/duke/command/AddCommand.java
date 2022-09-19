package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Represents command for adding a new task to taskList
 */
public class AddCommand extends Command{

    private final TaskType taskType;
    private final String arguments;

    public AddCommand(TaskType taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    /**
     * Adds new task of type taskType, with parameters arguments, to taskList
     * Informs user if task addition is successful
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        try {
            String task = taskList.addTask(taskType, arguments);
            ui.output("Task added:", task);
        } catch (DukeException e) {
            e.handle(ui);
        }
    }
}
