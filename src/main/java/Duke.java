import java.util.Scanner;

public class Duke {
    public static void printLines() {
        String lines = "__________________________________________________";
        System.out.println(lines);
    } // more compact compared to previous version in Level-0

    public static void main(String[] args) {

        printLines();
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        printLines();

        int count = 0; // how many items in array
        String input;
        boolean state = true;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        while (state) {
            input = in.nextLine();
            if (input.equals("bye")) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                state = false;
            }   else if (input.equals("list")) {
                printLines();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon()
                            + "] " + tasks[i].getDescription());
                }
                printLines();
            }   else {
                String[] words = input.split(" ");
                printLines();
                if (words[0].equals("mark")) {
                    tasks[Integer.parseInt(words[1]) - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + tasks[Integer.parseInt(words[1]) - 1].getStatusIcon()
                            + "] " + tasks[Integer.parseInt(words[1]) - 1].getDescription());
                } else if (words[0].equals("unmark")) {
                    tasks[Integer.parseInt(words[1]) - 1].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + tasks[Integer.parseInt(words[1]) - 1].getStatusIcon()
                            + "] " + tasks[Integer.parseInt(words[1]) - 1].getDescription());
                } else {
                    tasks[count] = new Task(input);
                    count++;
                    // System.out.println(count); // debug line
                    System.out.println("Added: " + input);
                }
                printLines();
            }
        }
    }
}