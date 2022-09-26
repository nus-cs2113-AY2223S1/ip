package duke.manager;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.command.Command;

public class TaskExecutor {

    private static final String MESSAGE_INDENTATION = "  ";

    private static void listCommand(int taskNumber) {
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

    private static void markCommand(int taskPosition) {
        Task task = TaskList.get(taskPosition - 1);
        if (task.isDone()) {
            System.out.println("You have already done this task!");
            return;
        }
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    private static void unmarkCommand(int taskPosition) {
        Task task = TaskList.get(taskPosition - 1);
        if (!task.isDone()) {
            System.out.println("You haven't done this task yet!");
            return;
        }
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                + MESSAGE_INDENTATION + task);
    }

    private static void deleteCommand(TaskList taskList, int taskPosition) {
        if (taskPosition < 0) {
            System.out.println("Please give me a positive number instead!");
            return;
        }   else if (taskPosition < taskList.getSize()) {
            System.out.println("☹ OOPS!!! You don't have that many tasks!");
            return;
        }
        taskPosition--;
        TaskList.deleteTask(taskPosition);
    }

    private static void todoCommand(TaskList taskList, String description) {
        Task task = new Todo(description);
        taskList.addTask(task);
    }

    private static void deadlineCommand(TaskList taskList, String description, String time) {
        Task task = new Deadline(description, time);
        taskList.addTask(task);
    }

    private static void eventCommand(TaskList taskList, String description, String time) {
        Task task = new Event(description, time);
        taskList.addTask(task);
    }

    private static void findCommand(TaskList taskList) {

    }

    public static void execute(TaskList taskList, Command c) {
        String keyword = c.getKeyword();
        switch (keyword) {
        case "bye":
            c.setBye(true);
            break;
        case "list":
            listCommand(taskList.getSize());
            break;
        case "mark":
            markCommand(Integer.parseInt(c.getArgument(true)));
            break;
        case "unmark":
            unmarkCommand(Integer.parseInt(c.getArgument(true)));
            break;
        case "delete":
            deleteCommand(taskList, Integer.parseInt(c.getArgument(true)));
            break;
        case "todo":
            todoCommand(taskList, c.getArgument(true));
            break;
        case "deadline":
            deadlineCommand(taskList, c.getArgument(true), c.getArgument(false));
            break;
        case "event":
            eventCommand(taskList, c.getArgument(true), c.getArgument(false));
            break;
        case "find":
            findCommand(taskList);
        default:
            break;
        }
    }

}
