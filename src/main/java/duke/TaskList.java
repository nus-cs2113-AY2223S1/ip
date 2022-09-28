package duke;

import java.io.IOException;
import java.util.ArrayList;


public class TaskList {

    private Storage storage;
    private Ui ui;

    protected static ArrayList<Task> taskList;

    public TaskList(Storage storage) {
        taskList = new ArrayList<Task>();
        this.storage = storage;
        ui = new Ui();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

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

    public void find(String line) {
        String[] words = line.split("\\s+", 2);
        if (words.length == 2 && words[1].trim().length() > 0) {
            ArrayList<Integer> matchingIndexes = new ArrayList<Integer>();
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getDescription().toLowerCase().contains(words[1].toLowerCase())) {
                    matchingIndexes.add(i);
                }
            }
            if (matchingIndexes.size() > 0) {
                ui.showMatchListSuccess();
                for (int i : matchingIndexes) {
                    ui.showListItem(i + 1, taskList.get(i).print());
                }
            } else {
                ui.showMatchListError();
            }
        } else {
            ui.showFindUsage();
        }
    }

}