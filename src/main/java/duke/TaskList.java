package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

public class TaskList {
    List<Task> items;

    public TaskList() {
        this(Storage.readDataFile());
    }

    public TaskList(List<Task> items) {
        this.items = new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }

    public Task getItem(int index) throws DukeException {
        if (index < 1 || index > items.size()) {
            throw new DukeException("Task at index " + index + " does not exist!");
        }
        return items.get(index - 1);
    }

    public void addItem(Task item) throws DukeException {
        items.add(item);
        Storage.writeDataFile(items);
    }

    public void deleteItem(Task task) throws DukeException {
        items.remove(task);
        Storage.writeDataFile(items);
    }

    public void markDone(Task task) throws DukeException {
        int index = items.indexOf(task);
        items.get(index).markDone();
        Storage.writeDataFile(items);
    }

    public void markUndone(Task task) throws DukeException {
        int index = items.indexOf(task);
        items.get(index).markUndone();
        Storage.writeDataFile(items);
    }

    public TaskList filter(String keyword) {
        final String searchKeyword = keyword.toLowerCase();
        List<Task> filteredItems = items.stream().filter(item -> item.getName().toLowerCase().contains(searchKeyword))
                .collect(Collectors.toList());
        return new TaskList(filteredItems);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, items.get(i)));
        }
        return sb.toString();
    }
}
