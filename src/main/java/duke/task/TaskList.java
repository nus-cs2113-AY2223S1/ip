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
     * Constructor of <code>TaskList</code> object which is temporary.
     * Temporary list is not stored in any storage, but only used for temporary task list operations.
     */
    public TaskList() {
        storage = null;
        tasks = new ArrayList<>();
    }

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
        if (!isTemporaryList()) {
            ui.showAddTaskSuccessMessage(task);
            save(ui);
        }
    }

    /**
     * List all the <code>Task</code> objects in the task list.
     * If there are tasks in the list, it shows the tasks in a numbered list, where the number represent the
     * If there are no tasks in the list and it is not a temporary list, then it shows a message that tell that there
     * are not tasks.
     * If the list is temporary and there is no task, then it is the case where a list is created just for the find
     * operation. The user interface will show that no matched tasks are found.
     * task number of the task.
     * @param ui User interface of the application.
     */
    public void list(Ui ui) {
        if (tasks.size() > 0) {
            ui.showTasks(tasks);
        } else if (!isTemporaryList()) {
            ui.showNoTaskMessage();
        } else {
            ui.showNoMatchingTaskMessage();
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

    /**
     * Return a <code>TaskList</code> object that contains all the matching tasks represented as
     * <code>Task</code> objects.
     * @param searchWord Search word to match tasks through their description.
     * @param ui User interface of the application
     * @return <code>TaskList</code> object of all the matching tasks.
     */
    public TaskList find(String searchWord, Ui ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks) {
            if (isMatched(task, searchWord)){
                matchingTasks.add(task, ui);
            }
        }

        return matchingTasks;
    }

    /**
     * Return a boolean value of whether the description of the task contains the specific search word.
     * @param task <code>Task</code> object to be compared with the search word.
     * @param searchWord Search word to check if it is a substring in the given task's description.
     * @return Boolean value of whether the search word is in the description of the task.
     */
    private boolean isMatched(Task task, String searchWord) {
        return task.description.toLowerCase().contains(searchWord.toLowerCase());
    }

    /**
     * Return a boolean value of whether the list is temporary.
     * If the list is temporary, no storage is needed.
     * @return Boolean value of whether the list is temporary.
     */
    private boolean isTemporaryList() {
        return storage == null;
    }
}
