import java.util.Scanner;

public class Duke {
    private static void greet() {
        String message = "Hello! I'm Ever\n" +
                "What can I do for you?";
        System.out.println(message);
    }
    private static void exit() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }
    public static void main(String[] args) {
        String logo = " ________      ________ _____  \n" +
                "|  ____\\ \\    / /  ____|  __ \\ \n" +
                "| |__   \\ \\  / /| |__  | |__) |\n" +
                "|  __|   \\ \\/ / |  __| |  _  / \n" +
                "| |____   \\  /  | |____| | \\ \\ \n" +
                "|______|   \\/   |______|_|  \\_\\";
        System.out.println(logo);

        greet();

        /* Get input from user */
        Scanner scanner = new Scanner(System.in);
        String inputMessage = "";

        while (true) {
            System.out.print(">> ");
            inputMessage = scanner.nextLine();
            if (inputMessage.equals("bye")) {
                break;
            }
            System.out.println(inputMessage);
        }

        exit();
    }
}
