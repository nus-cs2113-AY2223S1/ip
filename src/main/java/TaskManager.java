import java.util.Scanner;

public class TaskManager {
    public static final int TASKS_SIZE = 100;
    public static final String DASH_SEPARATOR = "------------------------------------------------\n";
    public static Task[] tasks = new Task[TASKS_SIZE];
    private static int oneBasedIndex = 1;
    public static void printMark(Task task, boolean done) {
        System.out.println(DASH_SEPARATOR);
        task.markDone(done);
        System.out.println(DASH_SEPARATOR);
    }
    public static void printTask(Task task) {
        System.out.println(DASH_SEPARATOR);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + oneBasedIndex
                + " tasks in the list.");
        System.out.println(DASH_SEPARATOR);
        oneBasedIndex++;
    }

    public static void printList() {
        System.out.println(DASH_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < oneBasedIndex; i++) {
            System.out.print(i);
            System.out.println("." + tasks[i]);
        }
        System.out.println(DASH_SEPARATOR);
    }

    public static void receiveCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        while (!command.equals("bye")) {
            boolean isList = command.equals("list");
            if (isList) {
                printList();
            } else {
                String firstWord = command.substring(0, command.indexOf(' '));
                doCommand(command, firstWord);
            }
            command = in.nextLine().trim();
        }
    }

    private static void doCommand(String command, String firstWord) {
        switch (firstWord) {
        case "mark":
            int pos = Integer.parseInt(command.substring("mark _".length()));
            printMark(tasks[pos], true);
            break;
        case "unmark":
            pos = Integer.parseInt(command.substring("unmark _".length()));
            printMark(tasks[pos], false);
            break;
        case "todo":
            tasks[oneBasedIndex] = new Todo(command, ' ');
            printTask(tasks[oneBasedIndex]);
            break;
        case "deadline":
            tasks[oneBasedIndex] = new Deadline(command, '/');
            printTask(tasks[oneBasedIndex]);
            break;
        case "event":
            tasks[oneBasedIndex] = new Event(command, '/');
            printTask(tasks[oneBasedIndex]);
            break;
        }
    }
}
