import java.util.Scanner;

public class Ui {
    Ui() {

    }
    String showLoadingError() {
        return "";
    }
    void showWelcome() {
        String line = "---------------------------------------------";
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
    }

    void showLine() {
        System.out.println("---------------------------------------------");
    }

    String readCommand() {
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        return text;
    }

    String showError(String errorMessage) {
        return errorMessage;
    }



}
