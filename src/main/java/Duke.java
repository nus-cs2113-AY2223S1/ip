import java.util.Scanner;

public class Duke {
    private static final String INDENT = "    ";
    private static final String HORIZONTAL_RULE = "____________________________________________________________\n";
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String[] GREETING = {
            "Hello! I'm Duke",
            "What can I do for you?"
    };
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static void displayMessage(String[] lines) {
        System.out.println(INDENT + HORIZONTAL_RULE);
        for (String line : lines) {
            System.out.println(INDENT + " " + line);
        }
        System.out.println(INDENT + HORIZONTAL_RULE);
    }

    public static void displayMessage(String line) {
        System.out.println(INDENT + HORIZONTAL_RULE);
        System.out.println(INDENT + " " + line);
        System.out.println(INDENT + HORIZONTAL_RULE);
    }

    public static void processInput(String input) {
        displayMessage(input);
    }

    public static void main(String[] args) {
        System.out.println(DUKE_LOGO);
        displayMessage(GREETING);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            processInput(input);
        }
        displayMessage(EXIT_MESSAGE);
        scanner.close();
    }
}
