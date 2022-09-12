package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Response {

    public static void markResponse(Task[] tasks, String description) {

        int taskPosition;
        try {
            taskPosition = ExceptionHandler.handleNotInteger(description);
            ExceptionHandler.handleOutOfBounds(tasks, taskPosition, "mark");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't think " + description + " is a number. :-(");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but " + description + " is beyond my scope.  :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            if (Task.getTaskNumber() == 0) {
                description = "any";
            }
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " tasks. :-(");
        }
    }

    public static void unmarkResponse(Task[] tasks, String description) {

        int taskPosition;
        try {
            taskPosition = ExceptionHandler.handleNotInteger(description);
            ExceptionHandler.handleOutOfBounds(tasks, taskPosition, "unmark");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't think " + description + " is a number. :-(");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but " + description + " is beyond my scope.  :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            if (Task.getTaskNumber() == 0) {
                description = "any";
            }
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " tasks. :-(");
        }
    }

    public static void listResponse(Task[] tasks, int taskNumber) {

        if (taskNumber == 0) {
            System.out.println("You don't have any tasks yet. Why not try creating some?");
        }   else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskNumber; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    public static void toDoResponse(Task[] tasks, String description, int taskNumber) {

        tasks[taskNumber] = new Todo(description);
        GenericPrint.printTaskMessages(tasks, taskNumber);
    }

    public static void deadlineResponse(Task[] tasks, String description, String time, int taskNumber) {

        tasks[taskNumber] = new Deadline(description, time);
        GenericPrint.printTaskMessages(tasks, taskNumber);
    }

    public static void eventResponse(Task[] tasks, String description, String time, int taskNumber) {

        tasks[taskNumber] = new Event(description, time);
        GenericPrint.printTaskMessages(tasks, taskNumber);
    }
}
