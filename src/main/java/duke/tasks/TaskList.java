package duke.tasks;

import duke.error.exceptions.ItemNotFoundAtIndexException;
import duke.error.exceptions.ItemNotFoundInListException;
import duke.error.exceptions.NoStateChangeException;
import duke.io.Storage;
import duke.tasks.tasktypes.DeadlineTask;
import duke.tasks.tasktypes.EventTask;
import duke.ui.StringFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of each task item added to the program.
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
     * For if custom numbering is to be used for string formatting. If this list is empty,
     * use default numbering (just ascending order starting from 1).
     */
    private final List<Integer> numbers = new ArrayList<Integer>();

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
            int number;
            // use default numbering unless a numbering is provided
            if (numbers.isEmpty()) {
                number = itemCounter;
            } else {
                number = numbers.get(itemCounter - 1);
            }
            String prefix = number + ". ";
            itemCounter++;
            if (Integer.toString(number).length() == 1) {
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
     * @throws ItemNotFoundAtIndexException if item index is not within the bounds of the list
     */
    public void markItem(int index) throws ItemNotFoundAtIndexException, NoStateChangeException {
        try {
            tasks.get(index).setDone(true);
        }
        catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundAtIndexException(index);
        }
    }

    /**
     * Mark item as not yet done.
     *
     * @param index <b>0-based</b> index of desired item
     * @throws ItemNotFoundAtIndexException if item index is not within the bounds of the list
     */
    public void unmarkItem(int index) throws ItemNotFoundAtIndexException, NoStateChangeException {
        try {
            tasks.get(index).setDone(false);
        }
        catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundAtIndexException(index);
        }
    }

    /**
     * Delete item from list.
     *
     * @param index <b>0-based</b> index of item to delete
     * @return text of the deleted item
     * @throws ItemNotFoundAtIndexException if item is not within the bounds of the list
     */
    public String deleteItem(int index) throws ItemNotFoundAtIndexException {
        try {
            String itemText = getTextOfItem(index);
            tasks.remove(index);
            itemCount--;
            return itemText;
        }
        catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundAtIndexException(index);
        }
    }

    /**
     * Do a case-insensitive find for tasks that contain a given substring.
     *
     * @param substring substring to search for
     * @return {@link TaskList} instance that contain all tasks that contain the substring.
     * @throws ItemNotFoundInListException If item was not found in the list.
     */
    public TaskList findString(String substring) throws ItemNotFoundInListException {
        TaskList bufferTaskList = new TaskList();
        int itemCounter = 1;
        for (Task task : tasks) {
            String searchText = task.getText();
            String searchPostfixText = "";

            //include postfix text in search if applicable
            if (task instanceof DeadlineTask) {
                searchPostfixText = ((DeadlineTask) task).getDeadline();
            } else if (task instanceof EventTask) {
                searchPostfixText = ((EventTask) task).getEventDate();
            }

            //case-insensitive search
            searchText = searchText.toLowerCase();
            searchPostfixText = searchPostfixText.toLowerCase();
            substring = substring.toLowerCase();
            if (searchText.contains(substring) || searchPostfixText.contains(substring)) {
                bufferTaskList.addItem(task);
                bufferTaskList.addNumbering(itemCounter);
            }
            itemCounter++;
        }
        if (bufferTaskList.getItemCount() == 0) {
            throw new ItemNotFoundInListException(substring);
        }
        return bufferTaskList;
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

    public int getItemCount() {
        return itemCount;
    }

    public void addNumbering(int number) {
        numbers.add(number);
    }
}
