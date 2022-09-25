package duke.manager;

import duke.task.*;
import duke.command.Command;

public class TaskExecutor {

    private static final String MESSAGE_INDENTATION = "  ";

    public static void listResponse(int taskNumber) {

        if (taskNumber == 0) { // Guard Clause
            System.out.println("☹ OOPS!!! You don't have any tasks yet. Why not try creating some?");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNumber; i++) {
            Task task = TaskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    public static void markResponse(int taskPosition) {
        Task task = TaskList.get(taskPosition - 1);
        if (task.isDone()) {
            System.out.println("You have already done this task!");
            return;
        }
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    public static void unmarkResponse(int taskPosition) {
        Task task = TaskList.get(taskPosition - 1);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    public static void deleteResponse(int taskPosition) {
        if (taskPosition < 0) {
            System.out.println("Please give me a positive number instead!");
            return;
        }   else if (taskPosition < TaskList.getSize()) {
            System.out.println("☹ OOPS!!! You don't have that many tasks!");
            return;
        }
        taskPosition--;
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
            listResponse(TaskList.getSize());
            break;
        case "mark":
            markResponse(Integer.parseInt(c.getArgument(true)));
            break;
        case "unmark":
            unmarkResponse(Integer.parseInt(c.getArgument(true)));
            break;
        case "delete":
            deleteResponse(Integer.parseInt(c.getArgument(true)));
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
            break;
        }
    }
}
