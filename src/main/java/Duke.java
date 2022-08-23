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

        int count = 0; // how many items in array
        String input;
        String[] tasks = new String[100];
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                state = false;
            }   else if (input.equals("list")) {
                printLines();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLines();
            }   else {
                tasks[count] = input;
                count++;
                printLines();
                System.out.println("added: " + input);
                printLines();
            }
        }
    }
}