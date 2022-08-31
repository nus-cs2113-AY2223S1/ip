import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;

    public static void printLines() {
        String lines = "__________________________________________________";
        System.out.println(lines);
    }

    public static String truncateInput(String[] s) {
        String[] copy = Arrays.copyOfRange(s, 1, s.length);
        return String.join(" ", copy);
    }

    public static void printGreetings(String greeting) {
        if (greeting.equals("hello")) {
            // hello greeting
            printLines();
            System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
            printLines();
        }   else {
            // bye greeting
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    public static void markResponse(int position, Task[] tasks) {
        tasks[position].markAsDone();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks[position]);
    }

    public static void unmarkResponse(int position, Task[] tasks) {
        tasks[position].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                + tasks[position]);
    }

    public static void listResponse(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    public static void toDoResponse(Task[] tasks, String input, int count) {
        // System.out.println(count); // code for debugging purposes
        tasks[count] = new Todo(input);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[count] + System.lineSeparator() + "Now you have " + (count + 1)
                + " tasks in the list.");
    }

    public static void deadlineResponse(Task[] tasks, String input, int count) {
        // System.out.println(count); // code for debugging purposes
        int byPosition = input.indexOf("/by");
        String taskDescription = input.substring(0, byPosition);
        String time = input.substring(byPosition + 4);
        tasks[count] = new Deadline(taskDescription, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[count] + System.lineSeparator() + "Now you have "
                + (count + 1)  + " tasks in the list.");
    }

    private static void eventResponse(Task[] tasks, String input, int count) {
        // System.out.println(count); // code for debugging purposes
        int atPosition = input.indexOf("/at");
        String taskDescription = input.substring(0, atPosition);
        String time = input.substring(atPosition + 4);
        tasks[count] = new Event(taskDescription, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                +  tasks[count] + System.lineSeparator() + "Now you have "
                + (count + 1) + " tasks in the list.");
    }

    public static void main(String[] args) {
        // prints hello greeting
        printGreetings("hello");

        String inputs;
        int count = 0; // how many items in array, 0 at start.
        boolean systemState = true;
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
        Scanner in = new Scanner(System.in);

        while (systemState) {
            inputs = in.nextLine();
            String[] words = inputs.split(" ");
            String keyword = words[0].toLowerCase();
            // previous line fixes any problem of upper/lower case problems for the command keywords
            String truncatedInput = truncateInput(words);
            // truncatedInput is the input without the command keywords e.g. todo, list, mark...

            printLines();
            switch (keyword) {
            case "bye":
                printGreetings("bye");
                systemState = false;
                break;
            case "mark":
                markResponse(Integer.parseInt(words[1]) - 1, tasks);
                break;
            case "unmark":
                unmarkResponse(Integer.parseInt(words[1]) - 1, tasks);
                break;
            case "list":
                listResponse(tasks, count);
                break;
            case "todo":
                toDoResponse(tasks, truncatedInput, count);
                count++;
                break;
            case "deadline":
                deadlineResponse(tasks, truncatedInput, count);
                count++;
                break;
            case "event":
                eventResponse(tasks, truncatedInput, count);
                count++;
                break;
            default:
                // probably an exception/bad input  - leave for the future
                System.out.println("ERROR!");
                break;
            }   // putting this switch statement into another function causes some variables to not be incremented
            printLines();
        }
    }
}