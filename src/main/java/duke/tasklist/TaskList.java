package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> asList() {
        return tasks;
    }

    public TaskList findTasks(String searchTerm) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Task task = getTask(i);
            builder.append(String.format("%d.%s\n", i + 1, task));
        }
        return builder.toString();
    }
}
