import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of each To-Do item added to the program.
 */
public class ToDoList {
    /** list containing item */
    private final List<ToDoItem> items;

    /** count of number of items in list */
    private int itemCount = 0;

    /**
     * Constructor that initializes list of To-Do items.
     */
    public ToDoList() {
        items = new ArrayList<ToDoItem>();
    }

    /**
     * Adds item to list of items. Increments item count by 1.
     *
     * @param item item to be added
     * @return returns the <b>0-based</b> index of the added item
     */
    public int addItem(String item) {
        items.add(new ToDoItem(item));
        return ++itemCount - 1;
    }

    /**
     * Converts list to a string representation.
     *
     * @return string representation of the To-Do list
     */
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        // 1-based index
        int itemCounter = 1;

        // append each item to string (with attached prefix and line break)
        for (ToDoItem item : items) {
            String prefix = itemCounter++ + ". ";
            outputString.append(prefix).append(item).append("\n");
        }

        // remove trailing linebreak
        return outputString.substring(0, outputString.length() - 1);
    }

    /**
     * Mark item as done.
     *
     * @param index <b>0-based</b> index of desired item
     */
    public void markItem(int index) {
        items.get(index).setDone(true);
    }

    /**
     * Mark item as not yet done.
     *
     * @param index <b>0-based</b> index of desired item
     */
    public void unmarkItem(int index) {
        items.get(index).setDone(false);
    }

    /**
     * Returns the text content of each item (without done, deadline tracker etc.)
     *
     * @param index <b>0-based</b> index of desired item
     * @return text content of desired item
     */
    public String getTextOfItem(int index) {
        return items.get(index).getText();
    }
}
