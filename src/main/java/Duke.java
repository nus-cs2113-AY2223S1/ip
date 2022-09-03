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
        String input = " ";
        String description;
        Scanner in = new Scanner(System.in);
        Boolean isMarked;
        input = in.nextLine();

        // duke runs until a "bye" is entered
        while (tasks.size() < 100 && !input.equals("bye")) {
            String [] splitLine = input.split(" ");
            isMarked = Boolean.FALSE;

            // list commands duke to list all the tasks stored and their completion status
            switch (splitLine[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    System.out.println();
                    break;
                case "mark":
                    // mark x commands duke to mark the corresponding task as completed
                    isMarked = Boolean.TRUE;
                    System.out.println("Nice! I've marked this task as done: ");
                    // without the break, both mark and unmark will trigger the similar process

                case "unmark":
                    // unmark x commands duke to mark the corresponding task as uncompleted
                    // Exceptions could occur
                    String numericString = input.substring(input.indexOf(" ") + 1);
                    int markedNum = Integer.parseInt(numericString) - 1;
                    tasks.get(markedNum).setStatus(isMarked);
                    if (!isMarked) {
                        System.out.println("Oh no :( I've marked it as uncompleted: ");
                    }
                    System.out.println("  " + tasks.get(markedNum) + "\n");
                    break;

                case "todo":
                    description = input.substring(input.indexOf(" ") + 1);
                    Task td = new Todo(description);
                    // this function does all the printing
                    taskPrint(tasks, td);
                    break;

                case "deadline":
                    description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by "));
                    String by = input.substring(input.indexOf("/by ") + 4);
                    Task d = new Deadline(description, by);
                    taskPrint(tasks, d);
                    break;

                case "event":
                    description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /at "));
                    String at = input.substring(input.indexOf("/at ") + 4);
                    Task e = new Event(description, at);
                    taskPrint(tasks, e);
                    break;

                default:
                    // other calls causes duke to add the user-input to tasks
                    Task t = new Task(input);
                    tasks.add(t);
                    System.out.println("added: " + input + "\n");
                    break;
            }
            input = in.nextLine();


        }

        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }

    /**
     * Method for printing Tasks adding
     * @param tasks
     * @param t
     * @return
     */
    public static void taskPrint(List<Task> tasks, Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: \n" + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
    }
}
