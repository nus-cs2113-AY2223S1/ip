import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    private static String[] textList = new String[100];
    private static int numberOfTexts = 0;
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println(input);
        printHorizontalLine();
    }

    public static void addItem(String input) {
        textList[numberOfTexts] = input;
        System.out.println("added: " + input);
        printHorizontalLine();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            }
            duke.addItem(input);
        }
    }
}
