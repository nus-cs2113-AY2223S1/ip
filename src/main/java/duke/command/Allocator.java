package duke.command;

import duke.Task;

public class Allocator {

    public static void allocateForResponse(Task[] tasks, String keyword, String description, String time, int taskNumber) {

        switch (keyword) {
        case "bye":
            Response.printGreetings("bye");
            break;
        case "mark":
            Response.markResponse(tasks, Integer.parseInt(description) - 1);
            break;
        case "unmark":
            Response.unmarkResponse(tasks, Integer.parseInt(description) - 1);
            break;
        case "list":
            Response.listResponse(tasks, taskNumber);
            break;
        case "todo":
            Response.toDoResponse(tasks, description, taskNumber);
            break;
        case "deadline":
            Response.deadlineResponse(tasks, description, time, taskNumber);
            break;
        case "event":
            Response.eventResponse(tasks, description, time, taskNumber);
            break;
        default:

            break;
        }
    }
}
