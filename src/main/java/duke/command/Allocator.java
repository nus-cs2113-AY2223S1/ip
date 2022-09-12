package duke.command;

import duke.task.Task;

public class Allocator {

    public static void processInput(String[] splitInput, String keyword, Task[] tasks) {

        Response.printLines();
        String description = StringTools.returnDescription(splitInput, keyword);
        String time = StringTools.returnTime(splitInput, keyword);
        int taskNumber = Task.getTaskNumber();
        allocateForResponse(tasks, keyword, description, time, taskNumber);
        Response.printLines();

    }
    public static void allocateForResponse(Task[] tasks, String keyword, String description, String time, int taskNumber) {

        switch (keyword) {
        case "bye":
            Response.printGreetings("bye");
            break;
        case "mark":
            Response.markResponse(tasks, description);
            break;
        case "unmark":
            Response.unmarkResponse(tasks, description);
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
