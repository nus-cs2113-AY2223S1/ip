package duke.task;

import java.util.ArrayList;

/**
 * TaskList class which contains an ArrayList of tasks to be manipulated
 * tasks can be added, deleted, searched, mark as done.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * find task in taskList using index
     *
     * @param index index of task
     * @return Task the task found
     */
    public Task findTask(int index) {
        return taskList.get(index);
    }

    /**
     * find task using search by string and returns a taskList containing tasks found
     *
     * @param word keyword to be searched
     * @return TaskList containing all the tasks with keyword
     */
    public TaskList findTasksBySearch(String word) {
        TaskList tasksFound = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(word)) {
                tasksFound.addTask(task);
            }
        }
        return tasksFound;
    }

    /**
     * add task to taskList
     *
     * @param toAdd task to be added
     */
    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    /**
     * delete task from taskList
     *
     * @param index of task to be deleted
     * @throws IndexOutOfBoundsException the exception thrown when index is more than size of taskList
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(index);
    }

    /**
     * get size of taskList
     *
     * @return size of taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * mark task as done
     *
     * @param index index of task to be marked done
     */
    public void markDone(int index) {
        taskList.get(index).markDone();
    }

    /**
     * mark task as undone
     *
     * @param index index of task to be marked undone
     */
    public void markUndone(int index) {
        taskList.get(index).markUndone();
    }
}
