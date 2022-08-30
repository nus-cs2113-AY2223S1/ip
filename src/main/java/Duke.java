import java.util.Scanner;
import java.util.Collections;


public class Duke {
    public static void main(String[] args) {
        showLogo();
        showWelcomeMsg();
        readUserCmd();
        showGoodbyeMsg();
    }

    private static void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    private static final String END_CMD = "bye";
    private static final int MAX_TASK = 100;
    private static final int SEPARATOR_LEN = 50;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASK];

    private static void showSeparator() {
        String separator = String.join("", Collections.nCopies(SEPARATOR_LEN, "="));
        System.out.println(separator);
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
        String input = sc.nextLine();
        while (!input.equals(END_CMD)) {
            switch (input.split(" ")[0]) {
            case "list":
                listTask();
                break;
            case "mark":
                markTask(Integer.parseInt(input.split(" ")[1]), true);
                break;
            case "unmark":
                markTask(Integer.parseInt(input.split(" ")[1]), false);
                break;
            default:
                addTask(input);
            }
            input = sc.nextLine();
        }
    }

    private static void addTask(String taskDescrip) {
        tasks[taskCount++] = new Task(taskDescrip);
        System.out.println(">>>Added: " + taskDescrip);
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
        }
        else {
            tasks[taskId-1].setMarked(mark);
            if (mark) {
                System.out.println(">>>Nice! I've marked this task as done:");
            }
            else {
                System.out.println(">>>OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks[taskId-1]);
        }
        showSeparator();
    }
}
