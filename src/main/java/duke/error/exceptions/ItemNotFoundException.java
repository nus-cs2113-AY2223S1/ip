package duke.error.exceptions;

import duke.Duke;

public class ItemNotFoundException extends CustomException {
    private final int index;
    public ItemNotFoundException(int index) {
        super();
        this.index = index + 1;
    }

    /**
     * Message to be used in dialog box
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
