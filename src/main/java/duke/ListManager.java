package duke;

import java.util.ArrayList;
import java.util.List;
import duke.task.Task;

public class ListManager {
    List<Task> items;

    public ListManager() {
        items = new ArrayList<>();
    }

    public Task getItem(int index) {
        if (index >= 1 && index <= items.size()) {
            return items.get(index - 1);
        } else {
            return null;
        }
    }

    public void addItem(Task item) {
        items.add(item);
    }

    public void markDone(int index) {
        if (index >= 1 && index <= items.size()) {
            items.get(index - 1).markDone();
        }
    }

    public void markUndone(int index) {
        if (index >= 1 && index <= items.size()) {
            items.get(index - 1).markUndone();
        }
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
