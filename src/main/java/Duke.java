import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();
        handleUserInput();
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputText = scanner.nextLine();
            while (inputText.trim().isEmpty())
                inputText = scanner.nextLine();
            switch (inputText) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                System.exit(0);
            default:
                System.out.println("    ____________________________________________________________");
                System.out.println("    "+inputText);
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }
    }
}
