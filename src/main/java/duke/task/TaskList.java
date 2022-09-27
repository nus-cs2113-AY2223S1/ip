package duke.task;

import duke.Ui;
import duke.command.CommandMenu;
import duke.Storage;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public static final String FILE_STRING_SEPARATOR = " // ";

    private final Storage storage;

    public TaskList() {
        storage = null;
        tasks = new ArrayList<>();
    }

    public TaskList(Ui ui) {
        storage = new Storage();
        tasks = new ArrayList<>(storage.loadTasks(ui));
    }

    public void add(Task task, Ui ui) {
        tasks.add(task);
        if (!isTemporaryList()) {
            ui.showAddTaskSuccessMessage(task);
            save(ui);
        }
    }

    public void list(Ui ui) {
        if (tasks.size() > 0) {
            ui.showTasks(tasks);
        } else if (!isTemporaryList()) {
            ui.showNoTaskMessage();
        } else {
            ui.showNoMatchingTaskMessage();
        }
    }

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

    public void save(Ui ui) {
        storage.saveTasks(tasks, ui);
    }

    public TaskList find(String searchWord, Ui ui) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks) {
            if (isMatched(task, searchWord)){
                matchingTasks.add(task, ui);
            }
        }

        return matchingTasks;
    }

    private boolean isMatched(Task task, String searchWord) {
        return task.description.toLowerCase().contains(searchWord.toLowerCase());
    }

    private boolean isTemporaryList() {
        return storage == null;
    }
}
