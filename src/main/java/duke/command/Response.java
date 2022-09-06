package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Response {

    public static void printLines() {
        System.out.println("___________________________________________________________________________");
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
    public static void markResponse(Task[] tasks, int position) {
        tasks[position].markAsDone();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks[position]);
    }

    public static void unmarkResponse(Task[] tasks, int position) {
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
        if (input.equals("")) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        tasks[count] = new Todo(input);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[count] + System.lineSeparator() + "Now you have " + (count + 1)
                + " tasks in the list.");
    }

    public static void deadlineResponse(Task[] tasks, String input, int count) {
        // System.out.println(count); // code for debugging purposes
        if (input.equals("")) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        int byPosition = input.indexOf("/by");
        String taskDescription = input.substring(0, byPosition);
        String time = input.substring(byPosition + 4);
        tasks[count] = new Deadline(taskDescription, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[count] + System.lineSeparator() + "Now you have "
                + (count + 1)  + " tasks in the list.");
    }

    public static void eventResponse(Task[] tasks, String input, int count) {
        // System.out.println(count); // code for debugging purposes
        if (input.equals("")) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        int atPosition = input.indexOf("/at");
        String taskDescription = input.substring(0, atPosition);
        String time = input.substring(atPosition + 4);
        tasks[count] = new Event(taskDescription, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                +  tasks[count] + System.lineSeparator() + "Now you have "
                + (count + 1) + " tasks in the list.");
    }
}
