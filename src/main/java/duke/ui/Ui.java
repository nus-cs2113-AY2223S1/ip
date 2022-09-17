package duke.ui;

import duke.common.Messages;

import static duke.common.Messages.MESSAGE_FAREWELL;
import static duke.common.Messages.MESSAGE_GREETING;
import static duke.common.Messages.MESSAGE_INQUIRY;
import static duke.common.Messages.MESSAGE_ADD_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_MARK_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_TASK_LISTING_HEADER;
import static duke.common.Messages.MESSAGE_COMMON_ERROR;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    //@@author wcwy-reused
    //Reused from from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
    // with minor modifications
    public void displayMessages(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
    //@@author

    public void displayGreetingMessage() {
        displayMessages(
                DIVIDER,
                MESSAGE_GREETING,
                MESSAGE_INQUIRY,
                DIVIDER
        );
    }

    public void displayListingMessage(String listContent) {
        displayMessages(
                MESSAGE_TASK_LISTING_HEADER,
                listContent,
                DIVIDER
        );
    }

    public void displayTaskAdditionMessage(String taskDetails, int count) {
        displayMessages(
                MESSAGE_ADD_TASK_ACKNOWLEDGEMENT,
                "\t" + taskDetails,
                "Now you have " + count + " tasks in the list",
                DIVIDER
        );
    }

    public void displayTaskDeletionMessage(String taskDetails, int count) {
        displayMessages(
                MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT,
                "\t" + taskDetails,
                "Now you have " + count + " tasks in the list",
                DIVIDER
        );
    }

    public void displayMarkOrUnmarkMessage(String taskName, boolean isMark) {
        displayMessages(
                getMessageAcknowledgementForMarkAndUnmark(isMark),
                getCheckboxForMarkAndUnmark(isMark) + taskName,
                DIVIDER
        );
    }

    public void displayExitMessage() {
        displayMessages(
                MESSAGE_FAREWELL,
                DIVIDER
        );
    }

    public void displayErrorMessage() {
        displayMessages(
                MESSAGE_COMMON_ERROR,
                DIVIDER
        );
    }

    private static String getMessageAcknowledgementForMarkAndUnmark(boolean isMark) {
        String output = "";
        if (isMark) {
            output = MESSAGE_MARK_TASK_ACKNOWLEDGEMENT;
        } else {
            output = MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT;
        }
        return output;
    }

    private static String getCheckboxForMarkAndUnmark(boolean isMark) {
        String output = "";
        if (isMark) {
            output = "\t[X] ";
        } else {
            output = "\t[ ] ";
        }
        return output;
    }
}
