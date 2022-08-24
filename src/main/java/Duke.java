import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return (description);
        }

        public void markStatus() {
            isDone = true;
        }

        public void unmarkStatus() {
            isDone = false;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        List<Task> Storage = new ArrayList<>();
        String line = " ";
        Scanner in = new Scanner(System.in);
        int count = 0;
        line = in.nextLine();

        while (count < 100 && !line.equals("bye")) {
            String [] splitLine = line.split(" ");

            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Storage.size(); i++) {
                    System.out.println(i+1 + ". [" + Storage.get(i).getStatusIcon() + "] "+ Storage.get(i).getDescription());
                }
                System.out.println();
            }
            else if (splitLine[0].equals("mark")) {
                String numericString = line.substring(line.indexOf(" ")+1);
                System.out.println(numericString);
                int markedNum = Integer.parseInt(numericString) - 1;
                Storage.get(markedNum).markStatus();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" [" + Storage.get(markedNum).getStatusIcon() + "] "+ Storage.get(markedNum).getDescription() + "\n");

                // need to consider that other stuff might cause an error like matcha
            }
            else if (splitLine[0].equals("unmark")) {
                String numericString = line.substring(line.indexOf(" ")+1);
                System.out.println(numericString);
                int markedNum = Integer.parseInt(numericString) - 1;
                Storage.get(markedNum).unmarkStatus();
                System.out.println("Okay, I've marked this task as not done yet sadly: ");
                System.out.println(" [" + Storage.get(markedNum).getStatusIcon() + "] "+ Storage.get(markedNum).getDescription() + "\n");

                // need to consider that other stuff might cause an error like matcha
            }

            else {
                Task t = new Task(line);
                Storage.add(t);
                System.out.println("added: " + line + "\n");
                count++;
            }
            line = in.nextLine();
        }


        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }
}
