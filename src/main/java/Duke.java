import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        // Greet user
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        // Wait for input
        do {
            line = in.nextLine();
            if (!line.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        line +
                        "\n____________________________________________________________\n");
            }
        } while (!line.equals("bye"));
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");

    }
}
