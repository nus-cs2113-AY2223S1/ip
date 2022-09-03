package duke.error.exceptions;

import duke.Duke;

public class ListEmptyException extends CustomException {
    @Override
    public String getExceptionMessage() {
        return String.format("Your to-do list is empty. Please add some items "
                + "to your list to use the <%1$s> command.", Duke.COMMAND_LIST);
    }
}
