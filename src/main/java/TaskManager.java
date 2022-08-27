import java.util.Scanner;

public class TaskManager {
    public static final String DASH_SEPARATOR = "------------------------------------------------\n";
    private static int index = 0;
    public static Task[] tasks = new Task[100];

    public static void doTasks() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();

        while(!command.equals("bye")){
            boolean isList = command.equals("list");
            boolean isMark = command.length() > 5 && command.startsWith("mark");
            boolean isUnmark =command.length() > 7 && command.startsWith("unmark");
            System.out.println(DASH_SEPARATOR);
            if (isList) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.print(i+1);
                    System.out.println(".[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
            } else if (isMark) {
                int pos = Integer.parseInt(command.substring(5)) - 1;
                tasks[pos].setDone(true);
                System.out.print("Nice! I've marked this task as done:\n  [X] ");
                System.out.println(tasks[pos].getDescription());
            } else if (isUnmark) {
                int pos = Integer.parseInt(command.substring(7)) - 1;
                tasks[pos].setDone(false);
                System.out.print("OK, I've marked this task as not done yet:\n  [ ] ");
                System.out.println(tasks[pos].getDescription());
            } else {
                tasks[index] = new Task(command);
                index++;
                System.out.println("added: " + command + "\n"
                + "Enter \"list\" to view all items you added or \"bye\" to exit.");
            }
            System.out.println(DASH_SEPARATOR);
            command = in.nextLine().trim();
        }
    }
}
