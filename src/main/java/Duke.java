import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int totalNumberOfTasks = -1;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        processInput();
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        input = scanner.nextLine();
        while (!input.equals("bye")) {
            printLine();
            if (input.equals("list")) {
                if (totalNumberOfTasks == -1) {
                    System.out.println("\tNothing in list right now!");
                    input = scanner.nextLine();
                    continue;
                }
                System.out.println("\tHere are the tasks in your list: ");
                for (int i = 0; i <= totalNumberOfTasks; i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                }
            } else if (input.split(" ")[0].equals("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNumber > totalNumberOfTasks || taskNumber < 0) {
                    System.out.println("\tInvalid input! Please key in an existing task number!");
                } else if (tasks[taskNumber].isDone()) {
                    System.out.println("\tThis task has already been marked as done!");
                } else {
                    System.out.println("\tNice! I've marked this task as done: ");
                    tasks[taskNumber].markAsDone();
                    System.out.println("\t  " + tasks[taskNumber]);
                }
            } else if (input.split(" ")[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNumber > totalNumberOfTasks || taskNumber < 0) {
                    System.out.println("\tInvalid input! Please key in an existing task number!");
                } else if (!tasks[taskNumber].isDone()) {
                    System.out.println("\tThis task has already been marked as not done!");
                } else {
                    System.out.println("\tOK, I've marked this task as not done yet: ");
                    tasks[taskNumber].markAsUndone();
                    System.out.println("\t  " + tasks[taskNumber]);
                }
            } else {
                tasks[++totalNumberOfTasks] = new Task(input);
                System.out.println("\tadded: " + input);
            }
            printLine();
            input = scanner.nextLine();
        }
        exit();
    }

    public static void exit() {
        printLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }
}
