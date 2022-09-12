package duke.command;

import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndicatorException;
import duke.exception.MissingIntegerException;
import duke.exception.MissingTimeException;
import duke.task.Task;

public class Allocator {

    public static void processInput(String[] splitInput, String keyword, Task[] tasks) {

        try {
            String description = StringTools.returnDescription(splitInput, keyword);
            ExceptionHandler.checkDescription(description, keyword);
            String time = StringTools.returnTime(splitInput, keyword);
            ExceptionHandler.checkTime(time, keyword);
            int taskNumber = Task.getTaskNumber();
            allocateForResponse(tasks, keyword, description, time, taskNumber);
        } catch (MissingIntegerException e) {
            System.out.println("☹ OOPS!!! You did not give me enough arguments! :-(");
        } catch (MissingIndicatorException e) {
            if (keyword.equals("deadline")) {
                System.out.println("☹ OOPS!!! You did not type /by! :-(");
            }   else if (keyword.equals("event")) {
                System.out.println("☹ OOPS!!! You did not type /at! :-(");
            }
        } catch (MissingDescriptionException e) {
            System.out.println("☹ OOPS!!! The description of a " + keyword + " cannot be empty.");
        } catch (MissingTimeException e) {
            System.out.println("☹ OOPS!!! You didn't give me the time for the " + keyword + " ! :-(");
        }
    }
    public static void allocateForResponse(Task[] tasks, String keyword, String description, String time, int taskNumber) {

        switch (keyword) {
        case "bye":
            GenericPrint.printGreetings("bye");
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
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
