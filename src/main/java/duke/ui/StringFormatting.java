package duke.ui;

import duke.Duke;
import duke.io.Storage;
import duke.tasks.TaskList;

/**
 * Class to help with formatting strings for printing in the UI.
 */
public class StringFormatting {

    /**
     * Line break character
     */
    public static final String LINE_BREAK = "\n";

    private static final String STRING_GREETING = "Good morning!";
    private static final String STRING_GOODBYE = "Alright! Your list has been saved successfully."
            + LINE_BREAK + LINE_BREAK + "See you around!";
    private static final String STRING_LOADED = "A save file was found and loaded successfully.";
    private static final String STRING_NOT_LOADED = "No save file was found, or the format was improper.";
    private static final String STRING_ASK_FOR_INPUT = "What would you like to do today?";
    private static final int INPUT_INDENT_SIZE = 4;


    /**
     * Formats a string to say how many tasks there are based on given integer.
     * Accounts for singular vs plural (1 task vs 2 tasks, is vs are).
     *
     * @param numberOfTasks number of tasks
     * @return formatted string
     */
    public static String formatNumberOfTasksString(int numberOfTasks) {
        if (numberOfTasks == 1) {
            return LINE_BREAK + "There " + "is"
                    + " currently " + numberOfTasks + " task in your list.";
        } else {
            return LINE_BREAK + "There " + "are"
                    + " currently " + numberOfTasks + " tasks in your list.";
        }
    }

    /**
     * Gets greeting string {@link StringFormatting#STRING_GREETING}
     *
     * @return greetings string
     */
    public static String getGreeting() {
        String bufferString = STRING_GREETING + LINE_BREAK + LINE_BREAK;
        if (Storage.isLoaded()) {
            bufferString += STRING_LOADED;
        } else {
            bufferString += STRING_NOT_LOADED;
        }
        return bufferString + formatNumberOfTasksString(Duke.FULL_TASK_LIST.getItemCount())
                + LINE_BREAK + LINE_BREAK + STRING_ASK_FOR_INPUT;
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
        return "Added \"" + textOfItem + "\" to your list.";
    }

    /**
     * Formats string for when an item is deleted.
     *
     * @param textOfItem task text
     * @return formatted string
     */
    public static String formatDeleteString(String textOfItem) {
        return "Deleted task \"" + textOfItem + "\" from your list.";
    }

    /**
     * Formats string for when an item is deleted.
     *
     * @param taskList {@link TaskList} item that is a subset of {@link Duke#FULL_TASK_LIST}, filtered by if it contains
     *                                 a substring for the {@link Duke#COMMAND_FIND} command.
     * @param substring                search substring
     * @return formatted string
     */
    public static String formatFindString(TaskList taskList, String substring) {
        String taskItems = taskList.toString();
        int numberOfItems = taskList.getItemCount();
        String formatString;
        if (numberOfItems == 1) {
            formatString = "There is %s (out of %s) item that contains the string \"%s\" in your list: ";
        } else {
            formatString = "There are %s (out of %s) items that contain the string \"%s\" in your list: ";
        }
        return String.format(formatString,
                numberOfItems, Duke.FULL_TASK_LIST.getItemCount(), substring)
                + LINE_BREAK + LINE_BREAK + taskItems;
    }

    /**
     * @return Formats the ask for input dialog at the bottom of the UI.
     */
    public static String formatInput() {
        return LINE_BREAK + "What would you like to do?" + LINE_BREAK + "INPUT:"
                + DialogBox.generateIndent(INPUT_INDENT_SIZE, DialogBox.INDENT_CHARACTER);
    }

    /**
     * Gets subcommand from a given input string.
     *
     * @param input input
     * @return subcommand of type string
     */
    public static String getCommandArgument(String input) {
        return input.split(Duke.STRING_DELIMITER, 2)[1].strip();
    }
}
