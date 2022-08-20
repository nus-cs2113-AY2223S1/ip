import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣴⣶⣶⣶⣶⣶⠶⣶⣤⣤⣀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⠁⠀⢀⠈⢿⢀⣀⠀⠹⣿⣿⣿⣦⣄⠀\n"
                + "⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⠿⠀⠀⣟⡇⢘⣾⣽⠀⠀⡏⠉⠙⢛⣿⣷⡖⠀\n"
                + "⠀⠀⠀⠀⠀⣾⣿⣿⡿⠿⠷⠶⠤⠙⠒⠀⠒⢻⣿⣿⡷⠋⠀⠴⠞⠋⠁⢙⣿⣄ \n"
                + "⠀⠀⠀⠀⢸⣿⣿⣯⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀⠉⢹⡄⠀⠀⠀⠛⠛⠋⠉⠹⡇ \n"
                + "⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀⣀⣠⣤⣤⣤⣤⣤⣤⣤⣼⣇⣀⣀⣀⣛⣛⣒⣲⢾⡷ \n"
                + "⢀⠤⠒⠒⢼⣿⣿⠶⠞⢻⣿⣿;i⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⣼⠃\n"
                + "⢮⠀⠀⠀⠀⣿⣿⣆⠀⠀⠻⣿⡿⠛⠉⠉⠁⠀⠉⠉⠛⠿⣿⣿⠟⠁⠀⣼⠃\n"
                + "⠈⠓⠶⣶⣾⣿⣿⣿⣧⡀⠀⠈⠒⢤⣀⣀⡀⠀⠀⣀⣀⡠⠚⠁⠀⢀⡼⠃\n"
                + "⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣷⣤⣤⣤⣤⣭⣭⣭⣭⣭⣥⣤⣤⣤⣴⣟⠁\n";

        System.out.println("    ____________________________________________________________");
        System.out.println(logo + "\n    Kon'nichiwa! Doraemon desu ≧◉◡◉≦\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void markDone(ArrayList<Task> tasks, String command, boolean isDone) {
        int taskIndex = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
        tasks.get(taskIndex).setStatus(isDone);

        System.out.println("    ____________________________________________________________");
        if (isDone == true) {
            System.out.println("    Subarashi! Good job in completing your task, Nobita:");
        } else {
            System.out.println("    Gambate Nobita, complete it soon! Don't procrastinate:");
        }
        System.out.println("    \uD83D\uDC49 [" + tasks.get(taskIndex).getStatusIcon() +
                "] " + tasks.get(taskIndex).getDescription());
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        printGreeting();
        String command;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon! ✪");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    \uD83D\uDC49 " + (i + 1) + ".[" + tasks.get(i).getStatusIcon()
                            + "] " + tasks.get(i).getDescription());
                }
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("mark ")) {
                markDone(tasks, command, true);
            } else if (command.startsWith("unmark ")) {
                markDone(tasks, command, false);
            } else {
                Task task = new Task(command);
                tasks.add(task);

                System.out.println("    ____________________________________________________________");
                System.out.println("    Added task: " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
