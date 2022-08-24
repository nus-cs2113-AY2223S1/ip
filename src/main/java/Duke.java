import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        int id = 1;

        List<Task> tasks = new ArrayList<Task>();
        do {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }

            if (line.equals("list")) {  // list out the items
                System.out.println("\t_____________________");
                System.out.println("\tHere are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println("\t" + task.id + "." + task.getStatusIcon() + task.description);

                }

                System.out.println("\t_____________________\n");
            } else {
                Task t = new Task(line, id);
                id += 1;
                tasks.add(t);
                System.out.println("\t_____________________");
                System.out.println("\t" + "added: " + t.description);
                System.out.println("\t_____________________\n");
            }

        } while (!line.equals("bye"));
    }
}
