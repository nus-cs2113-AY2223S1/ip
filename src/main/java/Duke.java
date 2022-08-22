import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            System.out.println(input);
        }
    }
}
