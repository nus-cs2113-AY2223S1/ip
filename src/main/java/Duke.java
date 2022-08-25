import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String linebreak = "____________________________________________________________";

    static void startSession() {
        System.out.println("Hello from\n" + logo);
        System.out.println(linebreak);
        System.out.println("Hello! I'm Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        System.out.println(linebreak);
    }

    static void endSession() {
        System.out.println(linebreak);
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        System.out.println(linebreak);
    }

    static void storeTasks(ArrayList<Task> Tasks, String text) {
        System.out.println(linebreak);
        Task newTask = new Task(text);
        Tasks.add(newTask);
        System.out.println("added: " + text);
        System.out.println(linebreak);
    }

    static void printTasks(ArrayList<Task> Tasks) {
        System.out.println(linebreak);
        int count = 1;
        for (Task i : Tasks) {
            System.out.println(String.valueOf(count) + "." + "[" + i.getStatusIcon() + "] " + i.getDescription());
            count++;
        }
        System.out.println(linebreak);
    }

    static void markTasks(ArrayList<Task> Tasks, String text) {
        System.out.println(linebreak);
        System.out.println("I've marked this task as done, now what?:");
        String[] result = text.split(" ");
        Tasks.get(Integer.valueOf(result[1]) - 1).setDone();
        System.out.println("[" + Tasks.get(Integer.valueOf(result[1]) - 1).getStatusIcon() + "] " + Tasks.get(Integer.valueOf(result[1]) - 1).getDescription());
        System.out.println(linebreak);
    }

    static void unmarkTasks(ArrayList<Task> Tasks, String text) {
        System.out.println(linebreak);
        System.out.println("I've marked this task as not done, get working!:");
        String[] result = text.split(" ");
        Tasks.get(Integer.valueOf(result[1]) - 1).setNotDone();
        System.out.println("[" + Tasks.get(Integer.valueOf(result[1]) - 1).getStatusIcon() + "] " + Tasks.get(Integer.valueOf(result[1]) - 1).getDescription());
        System.out.println(linebreak);
    }

    public static void main(String[] args) {
        ArrayList<Task> Tasks = new ArrayList<Task>();

        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.length() > 3 && line.substring(0, 4).equals("mark")) {
                markTasks(Tasks, line);
            } else if (line.length() > 4 && line.substring(0, 6).equals("unmark")) {
                unmarkTasks(Tasks, line);
            } else if (line.equals("list")) {
                printTasks(Tasks);
            } else {
                storeTasks(Tasks, line);
            }
            line = in.nextLine();
        }

        endSession();
    }
}
