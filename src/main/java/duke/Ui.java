package duke;

import duke.exception.DukeException;

import java.util.Collections;

/**
 * Interacts with user with prompts and messages.
 */
public class Ui {
    private static final int SEPARATOR_LEN = 50;

    public static void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public static void showSeparator() {
        String separator = String.join("", Collections.nCopies(SEPARATOR_LEN, "="));
        System.out.println(separator);
    }

    public static void showWelcomeMsg() {
        showSeparator();
        System.out.println("Hello! I'm Duke ^_^");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    public static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        showSeparator();
    }

    /**
     * Shows the error message in Duke system of given exception e.
     * @param e
     */
    public static void showError(DukeException e) {
        System.out.println("OOPS!!! " + e.getMessage());
    }
}
