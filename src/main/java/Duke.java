import java.util.Scanner;



public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        showWelcomeMsg();
        readUserCmd();
        showGoodbyeMsg();
    }

    private static final String ENDCMD = "bye";
    private static final int MAX_TASK = 100;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASK];

    private static void showSeparator() {
        System.out.println("===================================================");
    }

    private static void showWelcomeMsg() {
        showSeparator();
        System.out.println("Hello! I'm Duke ^_^");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    private static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon! qwq");
        showSeparator();
    }

    private static void readUserCmd() {
        Scanner sc = new Scanner(System.in);
        String input = new String();
        input = sc.nextLine();
        while (!input.equals(ENDCMD)) {
            switch (input) {
            case "list":
                listTask();
                break;
            default:
                addTask(new Task(input));
            }
            input = sc.nextLine();
        }
    }

    private static void addTask(Task task) {
        tasks[taskCount++] = task;
        System.out.println(">>>Added: " + task);
        showSeparator();
    }

    private static void listTask() {
        if (taskCount == 0) {
            System.out.println(">>>No Current Tasks.");
        } else {
            System.out.println(">>>Current Tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.print(">>>" + (i + 1) + ".");
                System.out.println(tasks[i]);
            }
            showSeparator();
        }
    }

    private static void markTask(int taskId, boolean mark) {
        if (taskId <= 0 || taskId > taskCount) {
            System.out.println(">>>Pls Enter the Right TaskId!");
            return;
        }
        else {
            tasks[taskId-1].setMarked(mark);
            if (mark) {
                System.out.println(">>>Nice! I've marked this task as done:");
            }
            else System.out.println(">>>OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskId-1]);
        }
        showSeparator();
    }
}
