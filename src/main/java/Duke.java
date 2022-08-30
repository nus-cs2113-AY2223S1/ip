import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String line;
        ArrayList<Task> dukeList = new ArrayList<Task>();
        int count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke\n" + "Duke: What can I do for you?");

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Duke: Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                System.out.println("Here are the tasks for today: ");
                for (Task i : dukeList) {
                    System.out.println(i);
                }
            } else if (line.startsWith("mark")) {
                int itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task toBeChanged = dukeList.get(itemNumber);
                if (toBeChanged.isDone) {
                    System.out.println("The task has already been completed! Try doing another task now :)");
                } else {
                    toBeChanged.markAsDone(toBeChanged);
                }
            } else if (line.startsWith("unmark")) {
                int itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task toBeChanged = dukeList.get(itemNumber);
                if (toBeChanged.isDone) {
                    toBeChanged.markAsUndone(toBeChanged);
                } else {
                    System.out.println("The task hasn't been completed. Do it soon :(");
                }
            } else if (line.startsWith("todo")) {
                String description = line.substring(line.indexOf("todo") + 5);
                Todo t = new Todo(description);
                dukeList.add(t);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                        t + System.lineSeparator() + "Now you have " + dukeList.size() + " tasks in the list");
            } else if (line.startsWith("deadline")) {
                String description = line.substring(line.indexOf("deadline") + 9, line.indexOf("/"));
                String by = line.substring(line.indexOf("by") + 3);
                Deadline d = new Deadline(description, by);
                dukeList.add(d);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                        d + System.lineSeparator() + "Now you have " + dukeList.size() + " tasks in the list");
            } else if (line.startsWith("event")) {
                String description = line.substring(line.indexOf("event") + 6, line.indexOf("/"));
                String time = line.substring(line.indexOf("at") + 3);
                Event e = new Event(description, time);
                dukeList.add(e);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                        e + System.lineSeparator() + "Now you have " + dukeList.size() + "tasks in the list.");
            } else {
                System.out.println("added: " + line);
                Task t = new Task();
                t.description = line;
                t.number = count + 1;
                t.isDone = false;
                dukeList.add(t);
            }
        }

    }
}
