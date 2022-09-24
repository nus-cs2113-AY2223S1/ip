package duke.ui;

import java.util.Scanner;

public class Ui {

    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    private static Scanner scanner;

    private void reply(String message) {
        System.out.print(HORIZONTAL_LINE + message + "\n" + HORIZONTAL_LINE);
    }

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        reply("     Hello! I'm Duke" + "\n" + "     What can I do for you?");
    }
    public void printByeMessage() {
        reply("     Bye. Hope to see you again soon!");
    }
    public void showLoadingError() {
        reply("     Sorry, the file doesn't seem to exist T_T");
    }
}
