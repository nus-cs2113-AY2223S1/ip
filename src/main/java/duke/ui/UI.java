package duke.ui;

import java.util.Scanner;

/**
 * Display class duke.ui.UI for duke chatbot & handle user input
 */
public class UI {
    private final Scanner in;
    public final String greetingArt = "greeting art placeholder \n";
    public final String goodbyeArt = "        .--'''''''''--.\n" +
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

    public final String dashLine = "______________________________________";
    public final String greetingMessage =
            "Hello! I'm Handsome!\n" +
                    greetingArt +
                    "How can I help you?";

    public final String goodbyeMessage = "Bye. Hope to see you again soon!\n" + goodbyeArt;


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
    public String readInput() {
        return in.nextLine();
    }

    /**
     * Prints formatted message
     */
    public void printMessageLine(String messageLine) {
        System.out.println(dashLine);
        System.out.println(messageLine);
        System.out.println(dashLine);
    }

    /**
     * Helper function to print greeting messages
     */
    public void printGreeting() {
        printMessageLine(greetingMessage);
    }

    /**
     * Helper function to print exit messages
     */
    public void printGoodbye() {
        printMessageLine(goodbyeMessage);
    }

}
