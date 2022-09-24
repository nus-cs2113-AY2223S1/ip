import java.util.Collections;

public class Ui {
    private static final int SEPARATOR_LEN = 50;

    private static void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    private static void showSeparator() {
        String separator = String.join("", Collections.nCopies(SEPARATOR_LEN, "="));
        System.out.println(separator);
    }

    private static void showWelcomeMsg() {
        showSeparator();
        System.out.println("Hello! I'm Duke ^_^");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    private static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon! qwq");
        showSeparator();
    }
}
