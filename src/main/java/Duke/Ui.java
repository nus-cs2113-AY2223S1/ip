package Duke;

import java.util.Scanner;

public class Ui {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static String readInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
