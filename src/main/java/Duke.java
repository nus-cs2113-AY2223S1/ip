import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static class Task {
        protected String description;
        protected int number;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatus() {
            if (isDone) {
                return "X";
            }
            return " ";
        }

        public boolean markAsDone(Task t) {
            System.out.println("Good job! You have completed another task! I've marked this task as done:\n" + "    [X] " + t.description);
            return true;
        }
        public boolean markAsUndone(Task t) {
            System.out.println("Ok, I've unmarked this task. Remember to do it soon!\n" + "    [ ] " + t.description);
            return false;
        }
    }

    public static char printCheck(Task t) {
        if (t.isDone) {
            return 'X';
        }
        return ' ';
    }
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
                for (Task i :dukeList) {
                    System.out.println(i.number + ": [" + printCheck(i) + "] " + i.description);
                }
            } else if (line.startsWith("mark")) {
                int itemNumber = Integer.parseInt(line.split(" ")[1]) -1;
                Task toBeChanged = dukeList.get(itemNumber);
                if (toBeChanged.isDone) {
                    System.out.println("The task has already been completed! Try doing another task now :)");
                } else {
                    toBeChanged.isDone = toBeChanged.markAsDone(toBeChanged);
                }
            } else if (line.startsWith("unmark")) {
                int itemNumber = Integer.parseInt(line.split(" ")[1]) -1;
                Task toBeChanged = dukeList.get(itemNumber);
                if (toBeChanged.isDone) {
                    toBeChanged.isDone = toBeChanged.markAsUndone(toBeChanged);
                } else {
                    System.out.println("The task hasn't been completed. Do it soon :(");
                }
            } else {
                System.out.println("added: " + line);
                Task t = new Task(line);
                t.description = line;
                t.number = count + 1;
                t.isDone = false;
                count++;
                dukeList.add(t);
            }
        }

    }
}
