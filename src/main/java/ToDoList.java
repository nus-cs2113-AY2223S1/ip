import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<ToDoItem> items;

    public ToDoList () {
        items = new ArrayList<ToDoItem>();
    }

    public void addItem (String item) {
        items.add(new ToDoItem(item));
    }

    public String toString () {
        StringBuilder outputString = new StringBuilder();
        int itemCounter = 1;
        for (ToDoItem item : items) {
            String prefix = Integer.toString(itemCounter++) + ". ";
            outputString.append(prefix).append(item).append("\n");
        }
        return outputString.toString().substring(0, outputString.length() - 1); //removes last trailing linebreak
    }

    public void markItem (int index) {
        items.get(index).setDone(true);
    }

    public void unmarkItem (int index) {
        items.get(index).setDone(false);
    }

    public String getTextOfItem (int index) {
        return items.get(index).getText();
    }

    public String getStringOfItem (int index) {
        return items.get(index).toString();
    }
}
