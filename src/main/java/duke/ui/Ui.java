package duke.ui;

import java.util.Scanner;


public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static String LINE = "    ____________________________________________________________";

    /**
     * Default Constructor
     */
    public Ui() {
    }

    /**
     * Prints welcome message to the command line
     */
    public void showWelcomeMessage() {
        String output =
                "Hello! I'm Duckymomo\n"
                + "What can I do for you?";

        formatAndPrint(output);
    }

    /**
     * Reads user input from command line
     * @return User input as string
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints formatted output
     * @param output Output to be formatted and printed
     */
    public void printOutput(String output) {
        formatAndPrint(output);
    }

    /**
     * Helper method to format messages to be printed on the terminal
     * @param input Message to be formatted
     */

    private void formatAndPrint(String input) {
        StringBuilder output = new StringBuilder();
        String[] split = input.split("\n");

        System.out.println(LINE);
        for (String string: split) {
            output.append(String.format("     %s\n", string));
        }
        System.out.print(output);
        System.out.println(LINE);
    }

}
