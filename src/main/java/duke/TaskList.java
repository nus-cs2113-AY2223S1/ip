package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */

public class TaskList {

    private Storage storage;
    private Ui ui;
    protected static ArrayList<Task> taskList;

    public TaskList(Storage storage) {
        taskList = new ArrayList<Task>();
        this.storage = storage;
        ui = new Ui();
    }

    /**
     * Returns the current size of the task list.
     *
     * @return Current size of task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task located at the specified position in the task list.
     *
     * @param index Index of task to be retrieved.
     * @return Task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a new task to the task list.
     * Optionally, print the task that was added.
     *
     * @param task Task to be added.
     * @param isPrint Set true to print added task.
     */
    public void addTask(Task task, boolean isPrint) {
        taskList.add(task);
        try {
            storage.writeToFile(this);
        } catch (IOException ioException) {
            ui.showUpdateFileError();
        }
        if (isPrint) {
            ui.showAddSuccess(task.print());
            ui.showSizeOfList(taskList.size());
        }
    }

    /**
     * Deletes a task at the specified position in the task list.
     * If invalid syntax of user command, prints correct syntax.
     * If index is out of range, prints allowed range of index.
     *
     * @param line Full user command.
     */
    public void delete(String line) {
        String[] words = line.split("\\s+");
        if (words.length == 2) {
            if (taskList.size() > 0) {
                try {
                    int d = Integer.parseInt(words[1]);
                    Task tempTask = taskList.get(d - 1);
                    taskList.remove(d - 1);
                    ui.showDeleteSuccess(tempTask.print());
                    ui.showSizeOfList(taskList.size());
                    storage.writeToFile(this);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    ui.showIndexError(taskList.size());
                } catch (NumberFormatException numberFormatException) {
                    ui.showDeleteUsage();
                } catch (IOException ioException) {
                    ui.showUpdateFileError();
                }
            } else {
                ui.showEmptyListError();
            }
        } else {
            ui.showDeleteUsage();
        }
    }

    /**
     * Constructs and adds a Todo task to the task list.
     *
     * @param line Full user command.
     */
    public void todo(String line) {
        String[] words = line.split("\\s+");
        if (words.length >= 2) {
            String task = line.substring(line.indexOf(' ') + 1);
            Todo todo = new Todo(task, false);
            addTask(todo, true);
        } else {
            ui.showTodoUsage();
        }
    }

    /**
     * Constructs and adds a Deadline task to the task list.
     *
     * @param line Full user command.
     */
    public void deadline(String line) {
        try {
            String task = line.substring(line.indexOf(' ') + 1, line.indexOf("/by") - 1);
            String date = line.substring(line.indexOf("/by") + 4);
            Deadline deadline = new Deadline(task, false, date);
            addTask(deadline, true);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            ui.showDeadlineUsage();
        }
    }

    /**
     * Constructs and adds a Event task to the task list.
     *
     * @param line Full user command.
     */
    public void event(String line) {
        try {
            String task = line.substring(line.indexOf(' ') + 1, line.indexOf("/at") - 1);
            String date = line.substring(line.indexOf("/at") + 4);
            Event event = new Event(task, false, date);
            addTask(event, true);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            ui.showEventUsage();
        }
    }

    /**
     * Prints all tasks in the task list.
     * If list is empty, an error message is printed.
     */
    public void list() {
        if (taskList.size() > 0) {
            ui.showList();
            for (int i = 0; i < taskList.size(); i++) {
                ui.showListItem(i + 1, taskList.get(i).print());
            }
        } else {
            ui.showEmptyListError();
        }
    }

    /**
     * Marks/unmarks tasks at the specified position in the task list as done.
     * If invalid syntax of user command, prints correct syntax.
     * If index is out of range, prints allowed range of index.
     * Notifies user if task is already marked/unmarked.
     *
     * @param isMark True to mark. False to unmark.
     * @param line Full user command.
     */
    public void mark(boolean isMark, String line) {
        String[] words = line.split("\\s+");
        if (words.length == 2) {
            try {
                int d = Integer.parseInt(words[1]);
                if (d > 0 && d <= taskList.size()) { // Valid
                    if (taskList.get(d-1).getStatus()) { // Task is done
                        if (isMark) {
                            ui.showTaskAlreadyDoneError(taskList.get(d - 1).print());
                        } else {
                            taskList.get(d - 1).setDone(false);
                            ui.showUnmarkSuccess(taskList.get(d - 1).print());
                        }
                    } else { // Task is not done
                        if (isMark) {
                            taskList.get(d - 1).setDone(true);
                            ui.showMarkSuccess(taskList.get(d - 1).print());
                        } else {
                            ui.showTaskAlreadyNotDoneError(taskList.get(d - 1).print());
                        }
                    }
                    storage.writeToFile(this);
                } else { // Invalid index
                    if (taskList.size() == 0) {
                        ui.showEmptyListError();
                    } else {
                        ui.showIndexError(taskList.size());
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                if (isMark) {
                    ui.showMarkUsage();
                } else {
                    ui.showUnmarkUsage();
                }
            } catch (IOException ioException) {
                ui.showUpdateFileError();
            }
        } else {
            if (isMark) {
                ui.showMarkUsage();
            } else {
                ui.showUnmarkUsage();
            }
        }
    }

}