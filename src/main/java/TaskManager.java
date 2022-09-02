import java.util.Scanner;

public class TaskManager {
    public static final String DASH_SEPARATOR = "------------------------------------------------\n";
    public static Task[] tasks = new Task[100];
    private static int index = 0;
    public static void printMark(Task task, boolean done) {
        System.out.println(DASH_SEPARATOR);
        task.markDone(done);
        System.out.println(DASH_SEPARATOR);
    }
    public static void printTask(Task task) {
        index++;
        System.out.println(DASH_SEPARATOR);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + index
                + " tasks in the list.");
        System.out.println(DASH_SEPARATOR);
    }

    public static void printList() {
        System.out.println(DASH_SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks[i]);
        }
        System.out.println(DASH_SEPARATOR);
    }

    public static void recieveCommands() {
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
            int pos = Integer.parseInt(command.substring(5)) - 1;
            printMark(tasks[pos], true);
            break;
        case "unmark":
            pos = Integer.parseInt(command.substring(7)) - 1;
            printMark(tasks[pos], false);
            break;
        case "todo":
            tasks[index] = new Todo(command, ' ');
            printTask(tasks[index]);
            break;
        case "deadline":
            tasks[index] = new Deadline(command, '/');
            printTask(tasks[index]);
            break;
        case "event":
            tasks[index] = new Event(command, '/');
            printTask(tasks[index]);
            break;
        }
    }
}
