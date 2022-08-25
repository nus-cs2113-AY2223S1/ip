import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;


        /**
         * Creates Task Object
         * @param description
         */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * Returns the status of the task
         * @return status as "X" or " "
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        /**
         * Returns the Description of the task
         * @return description
         */
        public String getDescription() {
            return (description);
        }

        /**
         * Sets Status to done
         */
        public void setMarkStatus() {
            isDone = true;
        }

        /**
         * Sets Status to uncompleted
         */
        public void setUnmarkStatus() {
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

        //tasks is an array list collection of task objects
        List<Task> tasks = new ArrayList<>();
        String line = " ";
        Scanner in = new Scanner(System.in);
        int count = 0;
        line = in.nextLine();

        // duke runs until a "bye" is entered
        while (count < 100 && !line.equals("bye")) {
            String [] splitLine = line.split(" ");

            // list commands duke to list all the tasks stored and their completion status
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". [" + tasks.get(i).getStatusIcon() + "] "
                            + tasks.get(i).getDescription());
                }
                System.out.println();
            }

            // mark x commands duke to mark the corresponding task as completed
            else if (splitLine[0].equals("mark")) {
                // Exceptions could occur
                String numericString = line.substring(line.indexOf(" ")+1);
                System.out.println(numericString);
                int markedNum = Integer.parseInt(numericString) - 1;
                tasks.get(markedNum).setMarkStatus();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" [" + tasks.get(markedNum).getStatusIcon() + "] "
                        + tasks.get(markedNum).getDescription() + "\n");
            }

            // unmark x commands duke to mark the corresponding task as uncompleted
            else if (splitLine[0].equals("unmark")) {
                String numericString = line.substring(line.indexOf(" ")+1);
                System.out.println(numericString);
                int markedNum = Integer.parseInt(numericString) - 1;
                tasks.get(markedNum).setUnmarkStatus();
                System.out.println("Okay, I've marked this task as not done yet sadly: ");
                System.out.println(" [" + tasks.get(markedNum).getStatusIcon() + "] "
                        + tasks.get(markedNum).getDescription() + "\n");
            }

            // other calls causes duke to add the user-input to tasks
            else {
                Task t = new Task(line);
                tasks.add(t);
                System.out.println("added: " + line + "\n");
                count++;
            }
            line = in.nextLine();
        }


        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }
}
