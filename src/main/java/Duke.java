import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printLines() {
        String lines = "__________________________________________________";
        System.out.println(lines);
    }

    public static void main(String[] args) {

        printLines();
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        printLines();

        int count = 0; // how many items in array
        String inputs;
        boolean state = true;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        while (state) {
            inputs = in.nextLine();
            if (inputs.equals("bye")) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                state = false;
            }   else if (inputs.equals("list")) {
                printLines();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                printLines();
            }   else {
                String[] words = inputs.split(" ");
                printLines();
                if (words[0].equals("mark")) {
                    tasks[Integer.parseInt(words[1]) - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + tasks[Integer.parseInt(words[1]) - 1].getStatusIcon()
                            + "] " + tasks[Integer.parseInt(words[1]) - 1].getDescription());
                } else if (words[0].equals("unmark")) {
                    tasks[Integer.parseInt(words[1]) - 1].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + tasks[Integer.parseInt(words[1]) - 1].getStatusIcon()
                            + "] " + tasks[Integer.parseInt(words[1]) - 1].getDescription());
                } else {
                    String[] copy = Arrays.copyOfRange(words, 1, words.length);
                    String inputWithoutKeywords = String.join(" ", copy);
                    if (words[0].equals("todo")) {
                        tasks[count] = new Todo(inputWithoutKeywords);;
                        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                                + tasks[count] + System.lineSeparator() + "Now you have " + (count + 1)
                                + " tasks in the list.");
                        count++;
                    }   else if (words[0].equals("deadline")) {
                        int byPosition = inputWithoutKeywords.indexOf("/by");
                        String taskDescription = inputWithoutKeywords.substring(0, byPosition);
                        String time = inputWithoutKeywords.substring(byPosition + 4);
                        tasks[count] = new Deadline(taskDescription, time);
                        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                                + tasks[count] + System.lineSeparator()
                                + "Now you have " + (count + 1)  + " tasks in the list.");
                        count++;
                    }   else if (words[0].equals("event")) {
                        int atPosition = inputWithoutKeywords.indexOf("/at");
                        String taskDescription = inputWithoutKeywords.substring(0, atPosition);
                        String time = inputWithoutKeywords.substring(atPosition + 4);
                        tasks[count] = new Event(taskDescription, time);
                        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                                +  tasks[count] + System.lineSeparator()
                                + "Now you have " + (count + 1) + " tasks in the list.");
                        count++;
                    }   else {
                        // probably an exception/bad input  - leave for the future
                        System.out.println("ERROR!");
                    }
                }
                printLines();
            }
        }
    }
}