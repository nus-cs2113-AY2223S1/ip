package duke.ui;


public class Ui {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    public void showLoadingError() {
        showMessage("     Sorry! The file does not exist T_T");
    }

    public static void showWelcome() {
        showMessage("     Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    public static void showGoodbye() {
        showMessage("     Bye. Hope to see you again soon!");
    }

    public static void showMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public static void showError(String message) {
        if (message == null) {
            System.out.println("     T_T OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            System.out.println(message);
        }
    }
}
