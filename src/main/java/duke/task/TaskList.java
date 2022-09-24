package duke.task;

import duke.command.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList () {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    static void tryAddEvent(ArrayList<Task> tasks, String line) {
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

    static void addEvent(ArrayList<Task> tasks, String line) {
        String[] descriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = descriptionAt[0];
        String at = descriptionAt[1];
        Event newEvent = new Event(eventDescription, at);
        tasks.add(newEvent);
        int eventId = tasks.size() - 1;
        printNewTask(tasks.get(eventId), tasks.size());
    }

    static void tryAddDeadline(ArrayList<Task> tasks, String line) {
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

    private static void addDeadline(ArrayList<Task> tasks, String line) {
        String[] descriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        Deadline newDeadline = new Deadline(deadlineDescription, by);
        tasks.add(newDeadline);
        int deadlineId = tasks.size() - 1;
        printNewTask(tasks.get(deadlineId), tasks.size());
    }

    public static void tryAddTodo(ArrayList<Task> tasks, String line) {
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

    private static void addTodo(ArrayList<Task> tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        Todo newTodo = new Todo(todoDescription);
        tasks.add(newTodo);
        int todoId = tasks.size() - 1;
        printNewTask(tasks.get(todoId), tasks.size());
    }

    static void tryUnmarkTask(ArrayList<Task> tasks, String line) {
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

    private static void unmarkTask(ArrayList<Task> tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks.get(unmarkId).setNotDone();
        printUnmark(tasks.get(unmarkId));
    }

    static void tryMarkTask(ArrayList<Task> tasks, String line) {
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

    private static void markTask(ArrayList<Task> tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks.get(markId).setDone();
        printMark(tasks.get(markId));
    }

    private static void deleteTask(ArrayList<Task> tasks, int deleteId) {
        printHorizontalLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     " + tasks.get(deleteId));
        System.out.println("     Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printHorizontalLine();
        tasks.remove(deleteId);
    }

    static void tryDeleteTask(ArrayList<Task> tasks, String[] parsedInput) {
        int deleteId = Integer.parseInt(parsedInput[1]) - 1;
        try {
            if (deleteId > tasks.size()) {
                throw new DukeException();
            }
            deleteTask(tasks, deleteId);
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println("     T_T OOPS!!! There is no such task number");
            printHorizontalLine();
        }
    }
}
