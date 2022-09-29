package duke;

/**
 * Represents the UI messages that will be displayed to the user.
 */
public class UI {
    /**
     * Displays the divider to the user.
     */
    public void printDivider() {
        System.out.println("\t----------------------------------------------------");
    }

    /**
     * Displays the greeting message to the user when the program is first run.
     */
    public void printGreeting() {
        printDivider();
        System.out.println("\tHello! I'm Albot, your new best friend! ᕙ(`▿´)ᕗ");
        System.out.println("\tWhat can I do for you today, friend? ʕ•́ᴥ•̀ʔっ♡");
        System.out.println();
        printDivider();
    }

    /**
     * Displays the exit message to the user when before exiting the program.
     */
    public void printExitMessage() {
        printDivider();
        System.out.println("\tAww, you're leaving already? I'm gonna miss you (╥﹏╥)");
        System.out.println("\tBut anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥");
        System.out.println();
        printDivider();
    }

    /**
     * Displays the Empty Description error message to the user.
     */
    public void printEmptyDescriptionError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! The description of a task cannot be empty...");
        System.out.println();
        printDivider();
    }

    /**
     * Displays the Unrecognized Command error message to the user.
     */
    public void printUnrecognizedCommandError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means...");
        System.out.println();
        printDivider();
    }

    /**
     * Displays the Index Out of Bounds error message to the user.
     */
    public void printIndexError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! Please provide a valid task index...");
        System.out.println();
        printDivider();
    }

    /**
     * Displays the File Loading error message to the user.
     */
    public void printLoadingError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! Unfortunately file is not found...");
        System.out.println();
        printDivider();
    }
}
