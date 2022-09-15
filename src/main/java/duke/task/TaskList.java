package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number is out of bound ☹"
                    + "\nThere are only " + taskList.size() + " task(s) in your list");
        }
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void markDone(int index) throws DukeException {
        getTask(index).setDone(true);
    }

    public void unmarkDone(int index) throws DukeException {
        getTask(index).setDone(false);
    }

    public void delete(int index) throws DukeException {
        taskList.remove(getTask(index));
    }

    public TaskList findTasksByKeyword(String keyword) {
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    public String formatTaskListToStringToStore() throws DukeException {
        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            formattedString.append(getTask(i + 1).formatTaskToStringToStore());
        }
        return formattedString.toString();
    }

    public String toStringFindResult() {
        StringBuilder listString = new StringBuilder((taskList.size() == 0 ? "There is no matching task in your list" : "Here are " + taskList.size() + " matching tasks in your list:"));
        int index = 1;
        for (Task task : taskList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(task);
        }
        return String.valueOf(listString);
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder((taskList.size() == 0 ? "There is nothing in your list right now" : "Here are " + taskList.size() + " tasks in your list:"));
        int index = 1;
        for (Task task : taskList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(task);
        }
        return String.valueOf(listString);
    }
}
