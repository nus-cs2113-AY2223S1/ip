import java.util.List;
import java.util.ArrayList;

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

    public void addItem(String item) {
        items.add(new Task(item));
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
            sb.append(String.format("%d.%s",
                    i + 1,
                    items.get(i)));
        }
        return sb.toString();
    }
}
