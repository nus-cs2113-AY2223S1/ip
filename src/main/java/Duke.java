import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


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
        String description;
        Scanner in = new Scanner(System.in);
        Boolean isMark;
        int count = 0;
        line = in.nextLine();

        // duke runs until a "bye" is entered
        while (count < 100 && !line.equals("bye")) {
            String [] splitLine = line.split(" ");
            isMark = Boolean.FALSE;

            // list commands duke to list all the tasks stored and their completion status
            switch (splitLine[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i+1 + ". " + tasks.get(i));
                    }
                    System.out.println();
                    break;
                case "mark":
                    // mark x commands duke to mark the corresponding task as completed
                    isMark = Boolean.TRUE;
                    System.out.println("Nice! I've marked this task as done: ");

                case "unmark":
                    // unmark x commands duke to mark the corresponding task as uncompleted
                    // Exceptions could occur
                    String numericString = line.substring(line.indexOf(" ")+1);
                    int markedNum = Integer.parseInt(numericString) - 1;
                    tasks.get(markedNum).setStatus(isMark);
                    if (!isMark) {
                        System.out.println("Oh no :( I've marked it as uncompleted: ");
                    }
                    System.out.println("  " + tasks.get(markedNum) + "\n");
                    break;

                case "todo":
                    description = line.substring(line.indexOf(" ")+1);
                    Task td = new Todo(description);
                    count = taskPrint(tasks, td, count);    // this function does all the printing and increases count also
                    //tasks.add(td);
                    //System.out.println("Got it. I've added this task: \n" + td);
                    //count++;
                    //System.out.println("Now you have " + count + " tasks in the list\n");
                    break;

                case "deadline":
                    description = line.substring(line.indexOf(" ")+1, line.indexOf(" /by "));
                    String by = line.substring(line.indexOf("/by ")+ 4);
                    Task d = new Deadline(description, by);
                    count = taskPrint(tasks, d, count);
                    //tasks.add(d);
                    //System.out.println("Got it. I've added this task: \n" + d);
                    //count++;
                    //System.out.println("Now you have " + count + " tasks in the list\n");
                    break;

                case "event":
                    description = line.substring(line.indexOf(" ")+1, line.indexOf(" /at "));
                    String at = line.substring(line.indexOf("/at ")+ 4);
                    Task e = new Event(description, at);

                    count = taskPrint(tasks, e, count);
                    //tasks.add(e);
                    //System.out.println("Got it. I've added this task: \n" + e);
                    //count++;
                    //System.out.println("Now you have " + count + " tasks in the list\n");
                    break;

                default:
                    // other calls causes duke to add the user-input to tasks
                    Task t = new Task(line);
                    tasks.add(t);
                    System.out.println("added: " + line + "\n");
                    count++;
                    break;
            }
            line = in.nextLine();


        }

        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }

    /**
     * Method for printing Tasks adding
     * @param tasks
     * @param t
     * @param count
     * @return
     */
    public static int taskPrint(List<Task> tasks, Task t, Integer count) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: \n" + t);
        count++;
        System.out.println("Now you have " + count + " tasks in the list\n");

        return count;
    }
}
