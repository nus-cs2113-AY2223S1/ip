package duke.task;

import duke.Ui;
import duke.command.CommandMenu;
import duke.Storage;

import java.util.ArrayList;

/**
 * Represent a resizable list of Task objects with the list operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    public static final String FILE_STRING_SEPARATOR = " // ";

    private final Storage storage;

    /**
     * Constructor of <code>TaskList</code> object with the specified user interface.
     * It initializes the storage and list of the tasks.
     * @param ui User interface of the application.
     */
    public TaskList(Ui ui) {
        storage = new Storage();
        tasks = new ArrayList<>(storage.loadTasks(ui));
    }

    /**
     * Add the <code>Task</code> object into the task list.
     * @param task <code>Task</code> object to be added to the task list.
     * @param ui User interface of the application.
     */
    public void add(Task task, Ui ui) {
        tasks.add(task);
        ui.showAddTaskSuccessMessage(task);
        save(ui);
    }

    /**
     * List all the <code>Task</code> objects in the task list.
     * If there are no tasks in the list, it shows a message that tell that there are not tasks.
     * If there are tasks in the list, it shows the tasks in a numbered list, where the number represent the
     * task number of the task.
     * @param ui User interface of the application.
     */
    public void list(Ui ui) {
        if (tasks.size() > 0) {
            ui.showTasks(tasks);
        } else {
            ui.showNoTaskMessage();
        }
    }

    /**
     * Mark the <code>Task</code> object specified by the task number as done.
     * @param taskNumber Task number representing the <code>Task</code> object to be processed.
     * @param ui User interface of the application
     */
    public void markTaskAsDone(int taskNumber, Ui ui) {
        try {
            int taskIndex = taskNumber - 1;
            Task task = tasks.get(taskIndex);
            task.markAsDone();
            ui.showMarkTaskSuccessMessage(task);
            save(ui);
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskNumberOutOfRangeMessage();
            ui.showCommandSyntaxHint(CommandMenu.MARK_COMMAND);
        }
    }

    /**
     * Mark the <code>Task</code> object specified by the task number as not done.
     * @param taskNumber Task number representing the <code>Task</code> object to be processed.
     * @param ui User interface of the application
     */
    public void markTaskAsUndone(int taskNumber, Ui ui) {
        try {
            int taskIndex = taskNumber - 1;
            Task task = tasks.get(taskIndex);
            task.unmarkDone();
            ui.showUnmarkTaskSuccessMessage(task);
            save(ui);
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskNumberOutOfRangeMessage();
            ui.showCommandSyntaxHint(CommandMenu.UNMARK_COMMAND);
        }
    }

    /**
     * Delete the <code>Task</code> object specified by the task number from the task list.
     * @param taskNumber Task number representing the <code>Task</code> object to be deleted.
     * @param ui User interface of the application.
     */
    public void delete(int taskNumber, Ui ui) {
        try {
            int taskIndex = taskNumber - 1;
            tasks.remove(taskIndex);
            ui.showDeleteTaskSuccessMessage(taskNumber);
            save(ui);
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskNumberOutOfRangeMessage();
            ui.showCommandSyntaxHint(CommandMenu.DELETE_COMMAND);
        }
    }

    /**
     * Save all the tasks in the list to the storage.
     * @param ui User interface of the application.
     */
    private void save(Ui ui) {
        storage.saveTasks(tasks, ui);
    }
}
