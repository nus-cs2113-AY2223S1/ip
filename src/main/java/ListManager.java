import java.util.List;
import java.util.ArrayList;

public class ListManager {
    List<String> items;

    public ListManager() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, items.get(i)));
        }
        return sb.toString();
    }
}
