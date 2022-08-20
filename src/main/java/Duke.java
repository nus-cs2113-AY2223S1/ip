import java.util.Scanner;

public class Duke {
    static String[] tasks = new String[100];
    static boolean[] isDone = new boolean[100];
    static int taskCount = 0;
    static void print() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i);
            System.out.print(" " + tasks[i] + " [");
            if (isDone[i]) {
                System.out.println("X]");
            } else {
                System.out.println(" ]");
            }
        }
    }
    static void add(String input) {
        tasks[taskCount] = input;
        isDone[taskCount] = false;
        taskCount++;
        System.out.print("Echo: Added " + input);
    }
    static void mark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        if (taskNum < taskCount) {
            isDone[taskNum] = true;
            System.out.println("Marked as done: " + tasks[taskNum]);
        }
    }
    static void unmark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        if (taskNum < taskCount && isDone[taskNum]) {
            isDone[taskNum] = false;
            System.out.println("Removed marking: " + tasks[taskNum]);
        }
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
            } else if (input.startsWith("mark")){
                mark(input);
            } else if (input.startsWith("unmark")) {
                unmark(input);
            } else {
                add(input);
            }
            System.out.println();
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon\n");
    }
}

