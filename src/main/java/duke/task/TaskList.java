package duke.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task findTask(int index) {
        return taskList.get(index);
    }

    public TaskList findTasksBySearch(String word) {
        TaskList tasksFound = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(word)) {
                tasksFound.addTask(task);
            }
        }
        return tasksFound;
    }

    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException{
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void markDone(int index) {
        taskList.get(index).markDone();
    }

    public void markUndone(int index) {
        taskList.get(index).markUndone();
    }
}
