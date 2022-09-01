
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        do {
            line = in.nextLine();
            String breakLine[] = line.split(" ", 2);
            String firstWord = breakLine[0];

            if (firstWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }

            if (firstWord.equals("list")) {  // list out the items
                System.out.println("\t_____________________");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                }


                System.out.println("\t_____________________\n");
            } else if (firstWord.equals("mark")) {
                int markId = Integer.parseInt(line.replaceAll("[^0-9]", ""));    // gets the id
                int taskId = markId - 1;
                System.out.println("\tNice! I've marked this task as done:");
                tasks[taskId].setDone(tasks[taskId].isDone);
                System.out.println("\t" + tasks[taskId].getStatusIcon() + tasks[taskId].getDescription());
            } else if (firstWord.equals("unmark")) {
                int markId = Integer.parseInt(line.replaceAll("[^0-9]", ""));    // gets the id
                int taskId = markId - 1;
                if (!tasks[taskId].isDone) {
                    System.out.println("this is already unmarked");
                } else {
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    tasks[taskId].setDone(tasks[taskId].isDone);
                    System.out.println("\t" + tasks[taskId].getStatusIcon() + tasks[taskId].getDescription());
                }

            } else if (firstWord.equals("total")) {
                System.out.println(taskCount);
            } else {

                if (firstWord.equals("todo")) {
                    String desc = breakLine[1];
                    Task t = new Todo(desc);
                    tasks[taskCount] = t;
                }
                else if (firstWord.equals("deadline")){
                    String desc = breakLine[1];
                    String breakBy[] = desc.split("/by", 2);
                    String detail = breakBy[0];
                    String by = breakBy[1];
                    Task d = new Deadline(detail, by);
                    tasks[taskCount] = d;
                }
                else if (firstWord.equals("event")){
                    String desc = breakLine[1];
                    String breakAt[] = desc.split("/at", 2);
                    String detail = breakAt[0];
                    String at = breakAt[1];
                    Task e = new Event(detail, at);
                    tasks[taskCount] = e;
                }

                System.out.println("\t_____________________");
                System.out.println("\t" + "Got it. I've added this task:");
                System.out.println("\t" + "added: " + tasks[taskCount]);
                System.out.println("\t_____________________\n");
                taskCount += 1;
            }

        } while (!line.equals("bye"));


    }
}
