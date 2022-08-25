import java.util.Scanner;

public class Duke {
    private static String[] items = new String[100];
    private static int itemsCount = 0;
    private static void greet() {
        String message = "Hello! I'm Ever\n" +
                "What can I do for you?";
        System.out.println(message);
    }
    private static void exit() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }
    private static void addItem(String item) {
        items[itemsCount++] = item;
    }
    private static void listItems() {
        for (int i = 0; i < itemsCount; i++) {
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
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
            } else if (inputMessage.equals("list")){
                listItems();
            } else {
                addItem(inputMessage);
                System.out.println("Item \"" + inputMessage + "\" added");
            }
        }

        exit();
    }
}
