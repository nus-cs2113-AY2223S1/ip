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
    }

    private static final String ENDCMD = "bye";
    private static final int MAX_TASK  = 100  ;
    private static int taskCount       = 0    ;
    private static String[] tasks = new String[MAX_TASK];

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
                addTask(input);
            }
            input = sc.nextLine();
        }
        showGoodbyeMsg();
    }

    private static void addTask(String task) {
        tasks[taskCount++] = task;
            System.out.println(">>>Added: " + task);
            showSeparator();
    }

    private static void listTask() {
        if (taskCount == 0) {
            System.out.println(">>>No Current Tasks.");
            showSeparator();
            return;
        }
        System.out.println(">>>Current tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(">>>" + (i + 1) + ".");
            System.out.println(tasks[i]);
        }
        showSeparator();
    }
 }
