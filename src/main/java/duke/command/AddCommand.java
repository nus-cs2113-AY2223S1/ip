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

    private TaskType taskType;
    private String arguments;

    public AddCommand(TaskType taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    /**
     * Adds new task of type taskType, with parameters arguments, to taskList
     * Informs user if task addition is successful
     * Informs user if new task is invalid
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        try {
            String task = TaskList.addTask(taskType, arguments);
            Ui.outputWithLines("Task added:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }
}
