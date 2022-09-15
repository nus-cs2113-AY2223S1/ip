package duke.ui;

import java.util.Scanner;


public class Ui {
    private Scanner sc = new Scanner(System.in);


    public Ui() {

    }

    public void showWelcomeMessage() {
        String output =
                "Hello! I'm Duckymomo\n"
                + "What can I do for you?";

        formatAndPrint(output);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void printOutput(String output) {
        formatAndPrint(output);
    }

    /**
     * Formats messages to be printed on the terminal
     * @param input Message to be formatted
     */

    private void formatAndPrint(String input) {
        StringBuilder output = new StringBuilder();
        String[] split = input.split("\n");

        System.out.println("    ____________________________________________________________");
        for (String string: split) {
            output.append(String.format("     %s\n", string));
        }
        System.out.print(output);
        System.out.println("    ____________________________________________________________");
    }

}
