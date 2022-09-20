package duke.ui;

import duke.common.InfoMessages;

import java.util.Scanner;

public class TextUi {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private Scanner in;

    public TextUi() {
        showWelcomeText();
        in = new Scanner(System.in);
    }

    public void printMessages(String... messages) {
        //@@author chydarren-reused
        // Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
        // with minor modifications
        System.out.println("    " + InfoMessages.MESSAGE_INFO_DIVIDER.toString());
        for (String message : messages) {
            System.out.println("    " + message);
        }
        System.out.println("    " + InfoMessages.MESSAGE_INFO_DIVIDER.toString());
    }

    public String getUserInput() {
        String input = in.nextLine();
        return input;
    }

    public void showWelcomeText() {
        printMessages(
                "*******     *******   *******       **" + LINE_SEPARATOR
                        + "    /**////**   **/////** /**////**     ****" + LINE_SEPARATOR
                        + "    /**    /** **     //**/**   /**    **//**" + LINE_SEPARATOR
                        + "    /**    /**/**      /**/*******    **  //**" + LINE_SEPARATOR
                        + "    /**    /**/**      /**/**///**   **********" + LINE_SEPARATOR
                        + "    /**    ** //**     ** /**  //** /**//////**" + LINE_SEPARATOR
                        + "    /*******   //*******  /**   //**/**     /**" + LINE_SEPARATOR
                        + "    ///////     ///////   //     // //      //",
                InfoMessages.MESSAGE_INFO_WELCOME.toString()
        );
    }

    public void showCustomText(Enum message) {
        printMessages(
                message.toString()
        );
    }

    public void showTaskList(String tasksList) {
        printMessages(
                InfoMessages.MESSAGE_INFO_LIST.toString(),
                tasksList
        );
    }

    public void showAddTaskInfo(String taskDetails, int taskSize) {
        printMessages(
                InfoMessages.MESSAGE_INFO_TASK_ADDED.toString(),
                taskDetails,
                String.format("%s", String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT.toString(), taskSize))
        );
    }

    public void showMarkTaskInfo(String taskDetails, boolean isDone) {
        Enum message = (isDone ? InfoMessages.MESSAGE_INFO_TASK_MARKED : InfoMessages.MESSAGE_INFO_TASK_UNMARKED);
        printMessages(
                message.toString(),
                taskDetails
        );
    }

    public void showDeleteTaskInfo(String taskDetails, int taskSize) {
        printMessages(
                InfoMessages.MESSAGE_INFO_TASK_DELETED.toString(),
                taskDetails,
                String.format("%s", String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT.toString(), taskSize))
        );
    }
}

