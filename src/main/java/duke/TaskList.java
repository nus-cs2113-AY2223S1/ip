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

    public Task getItem(int index) {
        if (index >= 1 && index <= items.size()) {
            return items.get(index - 1);
        } else {
            return null;
        }
    }

    public void addItem(Task item) throws DukeException {
        items.add(item);
        Storage.writeDataFile(items);
    }

    public void deleteItem(int index) throws DukeException {
        if (index < 1 || index > items.size()) {
            throw new DukeException("Task at index " + index + " does not exist!");
        }
        items.remove(index - 1);
        Storage.writeDataFile(items);
    }

    public void markDone(int index) throws DukeException {
        if (index < 1 || index > items.size()) {
            throw new DukeException("Task at index " + index + " does not exist!");
        }
        items.get(index - 1).markDone();
        Storage.writeDataFile(items);
    }

    public void markUndone(int index) throws DukeException {
        if (index < 1 || index > items.size()) {
            throw new DukeException("Task at index " + index + " does not exist!");
        }
        items.get(index - 1).markUndone();
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
