package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;

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

    public static void toDoResponse(Task[] tasks, String description, int taskNum) {
        //System.out.println(description); // code for debugging purposes
        if (description.length() < 1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        tasks[taskNum] = new Todo(description);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskNum] + System.lineSeparator() + "Now you have " + (taskNum + 1)
                + " tasks in the list.");
    }

    public static void deadlineResponse(Task[] tasks, String description, String time, int taskNum) {
        //System.out.println(description); // code for debugging purposes
        if (description.length() < 1) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }

        tasks[taskNum] = new Deadline(description, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskNum] + System.lineSeparator() + "Now you have "
                + (taskNum + 1)  + " tasks in the list.");
    }

    public static void eventResponse(Task[] tasks, String description, String time, int taskNum) {
        // System.out.println(count); // code for debugging purposes
        if (description.length() < 1) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }

        tasks[taskNum] = new Event(description, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                +  tasks[taskNum] + System.lineSeparator() + "Now you have "
                + (taskNum + 1) + " tasks in the list.");
    }
}
