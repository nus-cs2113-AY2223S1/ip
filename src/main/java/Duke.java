import java.util.Scanner;

public class Duke {
    public static void printLines() {
        String lines = "__________________________________________________";
        System.out.println(lines);
    } // more compact compared to previous version in Level-0
    public static void main(String[] args) {

        printLines();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLines();

        String input;
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                state = false;
            } else {
                printLines();
                System.out.println(input);
                printLines();
            }
        }
    }
}