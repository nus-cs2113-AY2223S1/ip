import java.util.List;
import java.util.ArrayList;

public class ListManager {
    List<ListItem> items;

    public ListManager() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(new ListItem(item));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d.[%s] %s\n",
                    i + 1,
                    items.get(i).isDone() ? "X" : " ",
                    items.get(i).getName()));
        }
        return sb.toString();
    }
}
