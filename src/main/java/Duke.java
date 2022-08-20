import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints logo to standard out.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints greeting message to standard out.
     */
    public static void printGreetingMessage() {
        String greetingMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(greetingMessage);
    }

    /**
     * Prints goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(HORIZONTAL_LINE);
        printGreetingMessage();
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(HORIZONTAL_LINE);
            System.out.println(input);
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE);
        printGoodbyeMessage();
        System.out.println(HORIZONTAL_LINE);
    }
}
