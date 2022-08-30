import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int matchCount = 0;

        while(true) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                int counter = 1;
                System.out.println("Here are the tasks in your list");
                for (Task task : tasks) {
                    if (task != null) {
                        System.out.println(counter + ".[" + task.getStatusIcon() + "] "+ task.getDescription());
                        counter++;
                    }
                }
            } else if (line.startsWith("mark")) {
                int num = Integer.parseInt(words[1]);
                if (num <= (matchCount)) {
                    tasks[num - 1].markDone();
                    System.out.println("[" + tasks[num - 1].getStatusIcon() + "] " + tasks[num -1].getDescription());
                }
            } else if (line.startsWith("unmark")) {
                int num = Integer.parseInt(words[1]);
                if (num <= (matchCount)) {
                    tasks[num - 1].markUndone();
                    System.out.println("[" + tasks[num - 1].getStatusIcon() + "] " + tasks[num - 1].getDescription());
                }
            } else {
                Task newTask = new Task(line);
                tasks[matchCount] = newTask;
                System.out.println("added " + line);
                matchCount += 1;
            }
        }
    }
}
