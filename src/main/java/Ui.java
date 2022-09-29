import java.util.Scanner;

/**
 * Ui Class.
 */
public class Ui {

    /**
     * empty UI Constructor.
     */
    Ui() {
    }

    /**
     * show error message when there is loading issues.
     */
    String showLoadingError() {
        return "Loading Error!";
    }

    /**
     * show welcome message to users when program is being started.
     */
    void showWelcome() {
        String line = "---------------------------------------------";
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
    }

    /**
     * line in UI.
     */
    void showLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * method used to read the commands that user inputs.
     */
    String readCommand() {
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        return text;
    }

    /**
     * methods that helps to show the specific error message when error encountered.
     */
    String showError(String errorMessage) {
        return errorMessage;
    }



}
