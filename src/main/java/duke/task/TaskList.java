package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> tasks;

    public void add(Task task) {
        tasks.add(task);
    }
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
     * @param statement the description and time of the event.
     */
    public static void tryAddEvent(ArrayList<Task> tasks, String statement) {
        try {
            if (statement.equals("")) {
                throw new DukeException();
            }
            if (!statement.contains(" /at ")) {
                throw new DukeException();
            }
            addEvent(tasks, statement);
            int eventId = tasks.size() - 1;
            printNewTask(tasks.get(eventId), tasks.size());
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! The statement of the event is not correct.");
        }
    }

    /**
     * Directly add an event, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param statement the description and time of the event.
     */
    public static void addEvent(ArrayList<Task> tasks, String statement) {
        String[] descriptionAt = statement.split("\\s+/at\\s+");
        String eventDescription = descriptionAt[0];
        String at = descriptionAt[1];
        Event newEvent = new Event(eventDescription, at);
        tasks.add(newEvent);
    }

    /**
     * Try to add a deadline, dealing with all the exceptions then adding.
     *
     * @param tasks the taskList.
     * @param statement the description and time of the deadline.
     */
    public static void tryAddDeadline(ArrayList<Task> tasks, String statement) {
        try {
            if (statement.equals("")) {
                throw new DukeException();
            }
            if (!statement.contains(" /by ")) {
                throw new DukeException();
            }
            addDeadline(tasks, statement);
            int deadlineId = tasks.size() - 1;
            printNewTask(tasks.get(deadlineId), tasks.size());
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! The statement of the deadline is not correct.");
        }
    }

    /**
     * Directly add a deadline, having dealt with the exception before.
     *
     * @param tasks the taskList.
     * @param statement the description and time of the deadline.
     */
    public static void addDeadline(ArrayList<Task> tasks, String statement) {
        String[] descriptionBy = statement.split("\\s+/by\\s+ ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        Deadline newDeadline = new Deadline(deadlineDescription, by);
        tasks.add(newDeadline);
    }

    /**
     * Try to add a todoTask, dealing with all the exceptions then adding.
     *
     * @param tasks the taskList.
     * @param statement the description of todoTask.
     */
    public static void tryAddTodo(ArrayList<Task> tasks, String statement) {
        try {
            if (statement.equals("")) {
                throw new DukeException();
            }
            addTodo(tasks, statement);
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
     * @param statement the description of todoTask.
     */
    public static void addTodo(ArrayList<Task> tasks, String statement) {
        Todo newTodo = new Todo(statement);
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
            if ((unmarkId >= tasks.size()) || (unmarkId < 0)) {
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
            if ((markId >= tasks.size()) || (markId < 0)) {
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
            if ((deleteId >= tasks.size()) || (deleteId < 0)) {
                throw new DukeException();
            }
            deleteTask(tasks, deleteId);
        } catch (DukeException e) {
            System.out.println("     T_T OOPS!!! There is no such task number");
        }
    }

    public static void findTask(ArrayList<Task> tasks, String statement) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(statement)) {
                result.add(task);
            }
        }
        if (result.getTasks().size() == 0) {
            System.out.println("     Sorry, no matching tasks.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < result.getTasks().size(); i++) {
                System.out.println("     " + (i + 1) + "." + result.getTasks().get(i));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
