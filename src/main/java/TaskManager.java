import java.util.Scanner;

public class TaskManager {
    public static final String DASH_SEPARATOR = "------------------------------------------------\n";
    private static int index = 0;
    public static Task[] tasks = new Task[100];

    public static void addTaskMsg(Task task) {
        index++;
        System.out.println(DASH_SEPARATOR);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + Integer.toString(index)
                + " tasks in the list.");
        System.out.println(DASH_SEPARATOR);
    }

    public static void doTasks() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        while(!command.equals("bye")){
            boolean isList = command.equals("list");
            String firstWord = " ";

            if (isList) {
                System.out.println(DASH_SEPARATOR);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.print(i+1);
                    System.out.println("." + tasks[i]);
                }
                System.out.println(DASH_SEPARATOR);
            } else {
                firstWord = command.substring(0, command.indexOf(' '));
            }
            switch (firstWord) {
            case "mark":
                int pos = Integer.parseInt(command.substring(5)) - 1;
                tasks[pos].markDone(true);
                break;
            case "unmark":
                pos = Integer.parseInt(command.substring(7)) - 1;
                tasks[pos].markDone(false);
                break;
            case "todo":
                tasks[index] = new Todo(command,' ');
                addTaskMsg(tasks[index]);
                break;
            case "deadline":
                tasks[index] = new Deadline(command, '/');
                addTaskMsg(tasks[index]);
                break;
            case "event":
                tasks[index] = new Event(command, '/');
                addTaskMsg(tasks[index]);
                break;
            }
            command = in.nextLine().trim();
        }
    }
}
