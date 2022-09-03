package duke.tasks;

import duke.error.exceptions.ItemNotFoundException;
import duke.error.exceptions.NoStateChangeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of each To-Do item added to the program.
 */
public class TaskList {
    /** List containing item */
    private final List<Task> items;

    /** Count of number of items in list */
    private int itemCount = 0;

    /**
     * Constructor that initializes list of task items.
     */
    public TaskList() {
        items = new ArrayList<Task>();
    }

    /**
     * Adds item to list of items. Increments item count by 1.
     *
     * @param item item to be added
     * @return returns the <b>0-based</b> index of the added item
     */
    public int addItem(Task item) {
        items.add(item);
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
        for (Task item : items) {
            String prefix = itemCounter++ + ". ";
            if (Integer.toString(itemCounter - 1).length() == 1) {
                outputString.append("0");
            }
            outputString.append(prefix).append(item).append("\n");
        }

        // remove trailing linebreak
        return outputString.substring(0, outputString.length() - 1);
    }

    /**
     * Mark item as done.
     *
     * @param index <b>0-based</b> index of desired item
     * @throws ItemNotFoundException if item index is not within the bounds of the list
     */
    public void markItem(int index) throws ItemNotFoundException, NoStateChangeException {
        try {
            items.get(index).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(index);
        }
    }

    /**
     * Mark item as not yet done.
     *
     * @param index <b>0-based</b> index of desired item
     * @throws ItemNotFoundException if item index is not within the bounds of the list
     */
    public void unmarkItem(int index) throws ItemNotFoundException, NoStateChangeException {
        try {
            items.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(index);
        }
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

    public int getItemCount() {
        return itemCount;
    }

}
