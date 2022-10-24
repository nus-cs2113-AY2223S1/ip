package Duke.UI;

/**
 * UI class manages the printing of various messages
 */
public class UI {
    public static final String PRINT_LINE = "____________________________________________________________\n";

    /**
     * Prints welcome message when program is first opened.
     */
    public static void WelcomeMessage() {
        String logo =
                " ___  __    ___  ________   ________          ________  ________  ________     \n" +
                        "|\\  \\|\\  \\ |\\  \\|\\   ___  \\|\\   ____\\        |\\   __  \\|\\   __  \\|\\   __  \\    \n" +
                        "\\ \\  \\/  /|\\ \\  \\ \\  \\\\ \\  \\ \\  \\___|        \\ \\  \\|\\ /\\ \\  \\|\\  \\ \\  \\|\\ /_   \n" +
                        " \\ \\   ___  \\ \\  \\ \\  \\\\ \\  \\ \\  \\  ___       \\ \\   __  \\ \\  \\\\\\  \\ \\   __  \\  \n" +
                        "  \\ \\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\       \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\|\\  \\ \n" +
                        "   \\ \\__\\\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\ \\_______\\\n" +
                        "    \\|__| \\|__|\\|__|\\|__| \\|__|\\|_______|        \\|_______|\\|_______|\\|_______|\n";

        System.out.println(logo
                + UI.PRINT_LINE
                + " Hello! I'm King Bob\n"
                + " What can I do for you?\n"
                + UI.PRINT_LINE
        );
    }

    /**
     * Prints exit message when program is closing.
     */
    public static void GoodbyeMessage() {
        System.out.println(
                UI.PRINT_LINE
                        + "Bye. Come back soon! :) \n"
                        + UI.PRINT_LINE
        );
    }

    /**
     * Prints error message when text file cannot load.
     */
    public static void loadingFileError() {
        System.out.println(
                UI.PRINT_LINE
                        + "Error loading file \n"
                        + UI.PRINT_LINE
        );
    }

}
