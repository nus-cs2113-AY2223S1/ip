import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = "     ____.  _____ ______________   ____.___  _________\n"
                + "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n"
                + "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n"
                + "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n"
                + "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n"
                + "                 \\/       \\/                       \\/ \n";

        String greeting = "------------------------------------------------\n"
                + "Hello! I'm\n"
                + logo
                + "What can I do for you?\n"
                + "------------------------------------------------\n";
        String parting = "------------------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------------------------------\n";

        System.out.println(greeting);
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        Task[] tasks = new Task[100];
        int idx = 0;
        while(!command.equals("bye")){
            System.out.println("------------------------------------------------\n");
            if (command.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    System.out.print(i+1);
                    System.out.println(".[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
            } else if (command.length() > 5 && command.substring(0,4).equals("mark")) {
                int pos = Integer.parseInt(command.substring(5)) - 1;
                tasks[pos].setDone(true);
                System.out.print("Nice! I've marked this task as done:\n  [X] ");
                System.out.println(tasks[pos].getDescription());
            } else if (command.length() > 7 && command.substring(0,6).equals("unmark")) {
                int pos = Integer.parseInt(command.substring(7)) - 1;
                tasks[pos].setDone(false);
                System.out.print("OK, I've marked this task as not done yet:\n  [ ] ");
                System.out.println(tasks[pos].getDescription());
            } else {
                tasks[idx] = new Task(command);
                idx++;
                System.out.println("added: " + command);
            }
            System.out.println("------------------------------------------------\n");
            command = in.nextLine().trim();
        }
        System.out.println(parting);
    }
}
