package duke;

import duke.command.TaskManager;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm duke.Duke\n"
                        + "What can I do for you?\n";
        System.out.println(greeting);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                TaskManager.list(TaskManager.tasks);
            } else if (input.startsWith("mark")){
                TaskManager.markAsDone(input, TaskManager.tasks);
            } else if (input.startsWith("unmark")) {
                TaskManager.markAsNotDone(input, TaskManager.tasks);
            } else if (input.equals("todo")) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } else if (input.equals("deadline")) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (input.equals("event")) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (input.startsWith("todo")) {
                TaskManager.addTodo(input, TaskManager.tasks);
            } else if (input.startsWith("deadline")) {
                TaskManager.addDeadline(input, TaskManager.tasks);
            } else if (input.startsWith("event")) {
                TaskManager.addEvent(input, TaskManager.tasks);
            } else if (input.startsWith("delete")) {
                TaskManager.deleteTask(input, TaskManager.tasks);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println();
            input = in.nextLine();
        }
        String farewell = "Bye. Hope to see you again soon!\n";
        System.out.println(farewell);
    }
}
