import java.util.Scanner;

public class Duke {
    static String[] tasks = new String[100];
    static int taskCount = 0;
    static void print() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i);
            System.out.println(" " + tasks[i]);
        }
    }
    static void add(String input) {
        tasks[taskCount] = input;
        taskCount++;
        System.out.println("Echo: Added " + input);
    }
    public static void main(String[] args) {
        String greeting = "Hello! I'm Timmy\n What can I do for you?\n \n";

        System.out.println(greeting);

        //Echo
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                print();
            } else {
                add(input);
            }
            System.out.println();
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon\n");
    }
}

