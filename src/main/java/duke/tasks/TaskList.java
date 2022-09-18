package duke.tasks;

import duke.error.exceptions.ItemNotFoundException;
import duke.error.exceptions.NoStateChangeException;
import duke.io.Storage;
import duke.ui.StringFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of each task item added to the program. Contains {@link Task} items.
 */
public class TaskList {
    /**
     * List containing item
     */
    private final List<Task> tasks;

    /**
     * Count of number of items in list
     */
    private int itemCount = 0;

    /**
     * Constructor that initializes list of task items.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Makes save string from all tasks in the list and concatenates into a single string
     * to be written to a file.
     *
     * @return save string
     */
    public String getSaveString() {
        String bufferString = "";
        for (Task task : tasks) {
            bufferString += Storage.formatSeparatedString(task.getSaveItems()) + StringFormatting.LINE_BREAK;
        }
        return bufferString;
    }

    /**
     * Adds item to list of items. Increments item count by 1.
     *
     * @param item item to be added
     * @return returns the <b>0-based</b> index of the added item
     */
    public int addItem(Task item) {
        tasks.add(item);
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
        for (Task item : tasks) {
            String prefix = itemCounter++ + ". ";
            if (Integer.toString(itemCounter - 1).length() == 1) {
                outputString.append("0");
            }
            outputString.append(prefix).append(item).append(StringFormatting.LINE_BREAK);
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
            tasks.get(index).setDone(true);
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
            tasks.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(index);
        }
    }

    /**
     * Delete item from list.
     *
     * @param index <b>0-based</b> index of item to delete
     * @return text of the deleted item
     * @throws ItemNotFoundException if item is not within the bounds of the list
     */
    public String deleteItem(int index) throws ItemNotFoundException {
        try {
            String itemText = getTextOfItem(index);
            tasks.remove(index);
            itemCount--;
            return itemText;
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
        return tasks.get(index).getText();
    }

    /**
     * Returns the number of items in the {@link TaskList} instance.
     *
     * @return number of items as an integer.
     */
    public int getItemCount() {
        return itemCount;
    }

}
