import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Timmy\n What can I do for you?\n \n";

        System.out.println(greeting);

        //Echo
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println("Echo: " + input);
            System.out.println();
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon\n");
    }
}

