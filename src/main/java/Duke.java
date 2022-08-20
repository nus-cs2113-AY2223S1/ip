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

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            command = in.nextLine();
            if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    \uD83D\uDC49 " + (i+1) + ".[" + tasks.get(i).getStatusIcon() + "] " +tasks.get(i).getDescription());
                }
                System.out.println("    ____________________________________________________________");
            }
            else if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon! ✪");
                System.out.println("    ____________________________________________________________");
                break;
            }
            else if (command.startsWith("unmark ")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Gambate Nobita, complete it soon! Don't procrastinate:");
                command = command.replaceAll("[^0-9]", "");
                int taskNumber = Integer.parseInt(command) - 1;
                tasks.get(taskNumber).setDone(false);
                System.out.println("    \uD83D\uDC49 [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                System.out.println("    ____________________________________________________________");
            }
            else if (command.startsWith("mark ")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Subarashi! Good job in completing your task, Nobita:");
                command = command.replaceAll("[^0-9]", "");
                int taskNumber = Integer.parseInt(command) - 1;
                tasks.get(taskNumber).setDone(true);
                System.out.println("    \uD83D\uDC49 [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                System.out.println("    ____________________________________________________________");
            }
            else {
                System.out.println("    ____________________________________________________________");
                Task t = new Task(command);
                tasks.add(t);
                System.out.println("    Added task: " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
