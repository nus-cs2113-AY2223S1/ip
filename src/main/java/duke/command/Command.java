package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class Command {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printNewTask(Task task) {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printTaskList(Task[] tasks) {
        printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("     " + tasks[i].getId() + "." + tasks[i]);
        }
        printHorizontalLine();
    }

    public static void printMark(Task task) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printHorizontalLine();
    }

    public static void printUnmark(Task task) {
        printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printHorizontalLine();
    }
    static void tryAddEvent(Task[] tasks, String line) {
        try {
            if (line.replaceFirst("event", "").trim().equals("")) {
                throw new DukeException();
            }
            addEvent(tasks, line);
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an event cannot be empty.");
            printHorizontalLine();
        }
    }
    static void addEvent(Task[] tasks, String line) {
        String[] descriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = descriptionAt[0];
        String at = descriptionAt[1];
        tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
        int eventId = Task.getNumberOfTasks() - 1;
        printNewTask(tasks[eventId]);
    }
    static void tryAddDeadline(Task[] tasks, String line) {
        try {
            if (line.replaceFirst("deadline", "").trim().equals("")) {
                throw new DukeException();
            }
            addDeadline(tasks, line);
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a deadline cannot be empty.");
            printHorizontalLine();
        }
    }
    private static void addDeadline(Task[] tasks, String line) {
        String[] descriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
        int deadlineId = Task.getNumberOfTasks() - 1;
        printNewTask(tasks[deadlineId]);
    }

    static void tryAddTodo(Task[] tasks, String line) {
        try {
            if (line.replaceFirst("todo", "").trim().equals("")) {
                throw new DukeException();
            }
            addTodo(tasks, line);
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a todo cannot be empty.");
            printHorizontalLine();
        }
    }

    private static void addTodo(Task[] tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
        int todoId = Todo.getNumberOfTasks() - 1;
        printNewTask(tasks[todoId]);
    }
    static void tryUnmarkTask(Task[] tasks, String line) {
        try {
            if (line.replaceFirst("unmark", "").trim().equals("")) {
                throw new DukeException();
            }
            unmarkTask(tasks, line.split(" "));
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an unmark cannot be empty.");
            printHorizontalLine();
        }
    }
    private static void unmarkTask(Task[] tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[unmarkId].setNotDone();
        printUnmark(tasks[unmarkId]);
    }

    static void tryMarkTask(Task[] tasks, String line) {
        try {
            if (line.replaceFirst("mark", "").trim().equals("")) {
                throw new DukeException();
            }
            markTask(tasks, line.split(" "));
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a mark cannot be empty.");
            printHorizontalLine();
        }
    }

    private static void markTask(Task[] tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[markId].setDone();
        printMark(tasks[markId]);
    }

    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printHorizontalLine();
    }

    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
