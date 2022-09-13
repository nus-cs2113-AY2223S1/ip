package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;
import duke.command.Command;
import duke.task.TaskList;

public class TaskExecutor {

    private static final String MESSAGE_INDENTATION = "  ";

    // to move message printing to UserInterface once stuff works

    public static void listResponse(TaskList taskList, int taskNumber) {

        if (taskNumber == 0) {
            System.out.println("☹ OOPS!!! You don't have any tasks yet. Why not try creating some?");
        }   else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskNumber; i++) {
                Task task = TaskList.get(i);
                System.out.println((i + 1) + "." + task);
            }
        }
    }

    public static void markResponse(int taskPosition) {
        Task task = TaskList.get(taskPosition);
        if (task.isDone()) {
            System.out.println("You have already done this task!");
            return;
        }
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    public static void unmarkResponse(int taskPosition) {
        Task task = TaskList.get(taskPosition);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    public static void deleteResponse(TaskList taskList, int taskPosition) {
        Task task = TaskList.get(taskPosition);
        System.out.println("Noted. I've removed this task:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task + System.lineSeparator()
                + "Now you have " + taskList.getSize() + "tasks in the list.");
        TaskList.deleteTask(taskPosition);
    }

    public static void todoResponse(TaskList taskList, String description) {
        Task task = new Todo(description);
        taskList.addTask(task);
    }

    private static void deadlineResponse(TaskList taskList, String description, String time) {
        Task task = new Deadline(description, time);
        taskList.addTask(task);
    }

    private static void eventResponse(TaskList taskList, String description, String time) {
        Task task = new Event(description, time);
        taskList.addTask(task);
    }

    public static void execute(TaskList taskList, Command c) {
        String keyword = c.getKeyword();
        switch (keyword) {
        case "bye":
            c.setBye(true);
            break;
        case "list":
            listResponse(taskList, TaskList.getSize());
            break;
        case "mark":
            markResponse(Integer.parseInt(c.getArgument(true)));
            break;
        case "unmark":
            unmarkResponse(Integer.parseInt(c.getArgument(true)));
            break;
        case "delete":
            deleteResponse(taskList, Integer.parseInt(c.getArgument(true)));
            break;
        case "todo":
            todoResponse(taskList, c.getArgument(true));
            break;
        case "deadline":
            deadlineResponse(taskList, c.getArgument(true), c.getArgument(false));
            break;
        case "event":
            eventResponse(taskList, c.getArgument(true), c.getArgument(false));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + keyword);
        }
    }
}
