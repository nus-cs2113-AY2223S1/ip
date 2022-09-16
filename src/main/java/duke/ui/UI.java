package duke.ui;

import java.util.Scanner;

/**
 * Display class duke.ui.UI for duke chatbot & handle user input
 */
public class UI {
    private static Scanner in = null;
    public static final String greetingArt = "greeting art placeholder \n";
    public static final String goodbyeArt = "        .--'''''''''--.\n" +
            "     .'      .---.      '.\n" +
            "    /    .-----------.    \\\n" +
            "   /        .-----.        \\\n" +
            "   |       .-.   .-.       |\n" +
            "   |      /   \\ /   \\      |\n" +
            "    \\    | .-. | .-. |    /\n" +
            "     '-._| | | | | | |_.-'\n" +
            "         | '-' | '-' |\n" +
            "          \\___/ \\___/\n" +
            "       _.-'  /   \\  `-._\n" +
            "     .' _.--|     |--._ '.\n" +
            "     ' _...-|     |-..._ '\n" +
            "            |     |\n" +
            "            '.___.'\n" +
            "              | |\n" +
            "             _| |_\n" +
            "            /\\( )/\\\n" +
            "           /  ` '  \\";

    /**
     * greeting and goodbye messages
     */

    public static final String DIVIDER = "______________________________________";
    public static final String greetingMessage =
            "Hello! I'm Handsome!\n" +
                    greetingArt +
                    "How can I help you?";

    public static final String goodbyeMessage = "Bye. Hope to see you again soon!\n" + goodbyeArt;


    /**
     * Constructor
     */
    public UI() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads input
     *
     * @return String containing user input
     */
    public static String readInput() {
        return in.nextLine();
    }

    /**
     * Prints formatted message
     */
    public static void printMessageWithLines(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    /**
     * Helper function to print greeting messages
     */
    public static void printGreeting() {
        printMessageWithLines(greetingMessage);
    }

    /**
     * Helper function to print exit messages
     */
    public static void printGoodbye() {
        printMessageWithLines(goodbyeMessage);
    }

}
