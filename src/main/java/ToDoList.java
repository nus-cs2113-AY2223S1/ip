import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<String> items;

    public ToDoList() {
        items = new ArrayList<String>();
    }

    public void addItem(String item) {
        items.add(item.trim());
    }

    public String toString() {
        StringBuilder outputString = new StringBuilder();
        int itemCounter = 1;
        for(String item : items){
            String prefix = Integer.toString(itemCounter++) + ". ";
            outputString.append(prefix).append(item).append("\n");
        }
        return outputString.toString().substring(0, outputString.length() - 1); //removes last trailing linebreak
    }
}
