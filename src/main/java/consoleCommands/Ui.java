package consoleCommands;

import java.util.Scanner;

public class Ui {
    public static final String HELLO_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void start() {
        printLine();
        System.out.println(HELLO_MESSAGE);
        printLine();
    }
    public void end() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }
    public Ui () {
    }
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void showError(String message) {
        System.out.println(message);
        printLine();
    }
}
