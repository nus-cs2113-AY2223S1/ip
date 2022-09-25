package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Show the adding message of the newly added task.
     *
     * @param task the new added task.
     * @param tasksSize the size of taskList.
     */
    public static void printNewTask(Task task, int tasksSize) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + tasksSize + " tasks in the list.");
    }


    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Show the mark message of the newly marked task.
     *
     * @param task the new marked task.
     */
    public static void printMark(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    /**
     * Show the unmark message of the newly unmarked task.
     *
     * @param task the new unmarked task.
     */
    public static void printUnmark(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
    }

    /**
     * Try to add an event, dealing with all the exceptions then adding.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void tryAddEvent(ArrayList<Task> tasks, String line) {
        try {
            if (line.replaceFirst("event", "").trim().equals("")) {
                throw new DukeException();
            }
            addEvent(tasks, line);
            int eventId = tasks.size() - 1;
            printNewTask(tasks.get(eventId), tasks.size());
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Directly add an event, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void addEvent(ArrayList<Task> tasks, String line) {
        String[] descriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = descriptionAt[0];
        String at = descriptionAt[1];
        Event newEvent = new Event(eventDescription, at);
        tasks.add(newEvent);
    }

    /**
     * Try to add a deadline, dealing with all the exceptions then adding.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void tryAddDeadline(ArrayList<Task> tasks, String line) {
        try {
            if (line.replaceFirst("deadline", "").trim().equals("")) {
                throw new DukeException();
            }
            addDeadline(tasks, line);
            int deadlineId = tasks.size() - 1;
            printNewTask(tasks.get(deadlineId), tasks.size());
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Directly add an deadline, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void addDeadline(ArrayList<Task> tasks, String line) {
        String[] descriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        Deadline newDeadline = new Deadline(deadlineDescription, by);
        tasks.add(newDeadline);
    }

    /**
     * Try to add a todoTask, dealing with all the exceptions then adding.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void tryAddTodo(ArrayList<Task> tasks, String line) {
        try {
            if (line.replaceFirst("todo", "").trim().equals("")) {
                throw new DukeException();
            }
            addTodo(tasks, line);
            int todoId = tasks.size() - 1;
            printNewTask(tasks.get(todoId), tasks.size());
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Directly add an todoTask, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void addTodo(ArrayList<Task> tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        Todo newTodo = new Todo(todoDescription);
        tasks.add(newTodo);
    }

    /**
     * Try to unmark a task, dealing with all the exceptions then unmark.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void tryUnmarkTask(ArrayList<Task> tasks, String line) {
        try {
            if (line.replaceFirst("unmark", "").trim().equals("")) {
                throw new DukeException();
            }
            int unmarkId = Integer.parseInt(line.replaceFirst("unmark ", "")) - 1;
            if ((unmarkId >= tasks.size()) || (unmarkId <= 0)) {
                throw new DukeException();
            }
            unmarkTask(tasks, line);
            String[] parsedInput = line.split(" ");
            unmarkId = Integer.parseInt(parsedInput[1]) - 1;
            printUnmark(tasks.get(unmarkId));
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! Please input the unmark id again.");
        }
    }

    /**
     * Directly unmark a task, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void unmarkTask(ArrayList<Task> tasks, String line) {
        String[] parsedInput = line.split(" ");
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks.get(unmarkId).setNotDone();
    }

    /**
     * Try to mark a task, dealing with all the exceptions then mark.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void tryMarkTask(ArrayList<Task> tasks, String line) {
        try {
            if (line.replaceFirst("mark", "").trim().equals("")) {
                throw new DukeException();
            }
            int markId = Integer.parseInt(line.replaceFirst("mark ", "")) - 1;
            if ((markId >= tasks.size()) || (markId <= 0)) {
                throw new DukeException();
            }
            markTask(tasks, line);
            String[] parsedInput = line.split(" ");
            markId = Integer.parseInt(parsedInput[1]) - 1;
            printMark(tasks.get(markId));
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! Please input the mark id again.");
        }
    }

    /**
     * Directly mark a task, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param line the parsed input.
     */
    public static void markTask(ArrayList<Task> tasks, String line) {
        String[] parsedInput = line.split(" ");
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks.get(markId).setDone();
    }

    /**
     * Directly delete a task, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param deleteId the id of the task to be deleted.
     */
    private static void deleteTask(ArrayList<Task> tasks, int deleteId) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + tasks.get(deleteId));
        System.out.println("     Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(deleteId);
    }

    /**
     * Try to delete a task, dealing with all the exceptions then delete.
     *
     * @param tasks the taskList.
     * @param parsedInput the parsed input.
     */
    public static void tryDeleteTask(ArrayList<Task> tasks, String[] parsedInput) {
        int deleteId = Integer.parseInt(parsedInput[1]) - 1;
        try {
            if ((deleteId >= tasks.size()) || (deleteId <= 0)) {
                throw new DukeException();
            }
            deleteTask(tasks, deleteId);
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! There is no such task number");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
