package duke.command;

import duke.task.Task;

public class Allocator {

    public static void allocate(Task[] tasks, String[] words, String keyword, String remainingInput) {

        switch (keyword) {
        case "bye":
            Response.printGreetings("bye");
            break;
        case "mark":
            Response.markResponse(tasks, Integer.parseInt(words[1]) - 1);
            break;
        case "unmark":
            Response.unmarkResponse(tasks, Integer.parseInt(words[1]) - 1);
            break;
        case "list":
            Response.listResponse(tasks, Task.getTaskNumber());
            break;
        case "todo":
            Response.toDoResponse(tasks, remainingInput, Task.getTaskNumber());
            break;
        case "deadline":
            Response.deadlineResponse(tasks, remainingInput, Task.getTaskNumber());
            break;
        case "event":
            Response.eventResponse(tasks, remainingInput, Task.getTaskNumber());
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
