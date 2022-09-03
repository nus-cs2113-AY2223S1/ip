package duke.error.exceptions;

import duke.Duke;

/**
 * Exception subclass of {@link DukeException} for if an item is not found in list at a given index.
 */
public class ItemNotFoundException extends DukeException {
    private final int index;

    /**
     * Constructor for exception.
     *
     * @param index index of item. <b>Note:</b> input is given as 0-based index
     *              but error message prints 1-based index
     */
    public ItemNotFoundException(int index) {
        super();
        this.index = index + 1;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        String tempString = String.format("Item at index %s was not found. ", index);
        if (index == 0) {
            tempString += "\nRemember that your to-do list is 1-indexed (0 is not valid).";
        }
        tempString += (Duke.TASK_LIST.getItemCount() == 0) ? "\nYour to-do list is currently empty." : String.format(
                "\nYour to-do list currently has %s item(s).", Duke.TASK_LIST.getItemCount());
        return tempString;
    }
}
