import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Retrieves command from user input
     *
     * @return the user input
     */
    public String getCommand() {
        String line = in.nextLine();
        return line;
    }

    /**
     * Displays error
     *
     * @param error to be printed
     */
    public void showError(String error) {
        print(error);
    }

    /**
     * Displays output
     *
     * @param output to be printed
     */
    public void displayOutput(String output) {
        print(output);
    }

    private static void print(String string) {
        System.out.println(HORIZONTAL_LINE + string + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Displays welcome message
     */
    public static void showWelcomeMessage() {
        print(" Hello! I'm Matthew\n" + " What can I do for you?");
    }

    public static void showGoodbyeMessage() {
        print("Bye. Hope to see you again soon!");
    }


}
