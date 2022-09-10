package duke.ui;

import duke.Duke;

/**
 * Class to help with formatting strings for printing in the UI.
 */
public class StringFormatting {

    private static final String STRING_GREETING = "Good morning!\nWhat would you like to do today?";
    private static final String STRING_GOODBYE = "Alright! Your list has been saved "
            + "successfully. \n\nSee you around!";

    private static final String STRING_LOADED = "A save file was found and automatically loaded.";

    /**
     * Formats a string to say how many tasks there are based on given integer.
     * Accounts for singular vs plural (1 task vs 2 tasks, is vs are).
     *
     * @param numberOfTasks number of tasks
     * @return formatted string
     */
    public static String formatNumberOfTasksString(int numberOfTasks) {
        return "\nThere " + (numberOfTasks == 1 ? "is" : "are")
                + " currently " + numberOfTasks + " task"
                + (numberOfTasks == 1 ? "" : "s") + " in your list.";
    }

    /**
     * Gets greeting string {@link StringFormatting#STRING_GREETING}
     *
     * @return greetings string
     */
    public static String getGreeting() {
        return STRING_GREETING + "\n\n" + STRING_LOADED
                + formatNumberOfTasksString(Duke.TASK_LIST.getItemCount());
    }

    /**
     * Gets goodbye string {@link StringFormatting#STRING_GOODBYE}
     *
     * @return goodbye string
     */
    public static String getGoodbye() {
        return STRING_GOODBYE;
    }

    /**
     * Formats string for marking/unmarking confirmation.
     *
     * @param textOfItem task text
     * @param done       true if task is being marked done, false if not.
     * @return formatted string
     */
    public static String formatMarkOrUnmarkString(String textOfItem, boolean done) {
        return "Marked task \"" + textOfItem + "\" as " + (done ? "done" : "not yet done") + ".";
    }

    /**
     * Formats string for when an item is added.
     *
     * @param textOfItem task text
     * @return formatted string
     */
    public static String formatAddString(String textOfItem) {
        return "Added " + textOfItem + " to your list.";
    }

    /**
     * Formats string for when an item is deleted.
     *
     * @param textOfItem task text
     * @return formatted string
     */
    public static String formatDeleteString(String textOfItem) {
        return "Deleted task " + textOfItem + " from your list.";
    }
}
