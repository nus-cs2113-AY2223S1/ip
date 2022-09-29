package duke.task;

import duke.exception.AbsentArgsFlagException;
import duke.exception.DukeException;
import duke.exception.IllegalArgsNumException;
import duke.exception.IllegalArgsTypeException;
import duke.Storage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a TaskList in the Duke system.
 * A TaskList provides operations on tasks (e.g. addTask).
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private static final String DEADLINE_FLAG = "/by";
    private static final String EVENT_FLAG = "/at";
    private static final String[] ILLEGAL_ARGS = {"blah"};

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }
    public static void setTaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Adds one task into taskList.
     * @param taskDescription
     * @throws IllegalArgsTypeException
     */
    public static void addTask(String taskDescription) throws IllegalArgsTypeException {
        if (Arrays.asList(ILLEGAL_ARGS).contains(taskDescription)) {
            throw new IllegalArgsTypeException();
        }
        Task new_task = new Task(taskDescription.trim());
        tasks.add(new_task);
        System.out.println(">>>Added: " + new_task);
        showTaskCount();
    }

    /**
     * Lists all tasks in the taskList.
     */
    public static void listTask() {
        if (tasks.size() == 0) {
            System.out.println(">>>No Current Tasks.");
        } else {
            System.out.println(">>>Current Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(">>>" + (i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
    }

    /**
     * Marks a task as done, if setMark is true,
     * or notDone, if setMark is false.
     * @param num_str
     * @param setMark
     */
    public static void markTask(String num_str, boolean setMark) {
        int num;
        if ((num = isLegalIndex(num_str)) == 0) return;
        else {
            tasks.get(num-1).setMarked(setMark);
            if (setMark) {
                System.out.println(">>>Nice! I've marked this task as done:");
            }
            else {
                System.out.println(">>>OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(num-1));
        }
    }

    /**
     * Deletes the specified task from task list.
     * @param num_str
     */
    public static void deleteTask(String num_str) {
        int num;
        if ((num = isLegalIndex(num_str)) == 0) return;
        Task tmp_task = tasks.get(num - 1);
        tasks.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(">>>" + tmp_task);
        showTaskCount();
    }

    /**
     * Add a todo object in taskList with description todoDescription
     * @param todoDescription
     * @throws IllegalArgsNumException
     */
    public static void addTodo(String todoDescription) throws IllegalArgsNumException {
        if (todoDescription.equals("")) {
            throw new IllegalArgsNumException();
        }
        Task new_todo = new Todo(todoDescription.trim());
        tasks.add(new_todo);
        System.out.println(">>>Added: " + new_todo);
        showTaskCount();
    }

    /**
     * Add a deadLine object in taskList with description and due time.
     * @param str
     * @throws DukeException
     */
    public static void addDeadline(String str) throws DukeException {
        if ("".equals(str)) {
            throw new IllegalArgsNumException();
        }

        String description = str.split("/")[0].trim();
        if (!str.contains(DEADLINE_FLAG)) {
            throw new AbsentArgsFlagException();
        }

        String by = str.split("/")[1];
        Task new_deadline = new Deadline(description, by);
        tasks.add(new_deadline);
        System.out.println(">>>Added: " + new_deadline);
        showTaskCount();
    }

    /**
     * Add an event object in taskList with description and happening time.
     * @param str
     * @throws DukeException
     */
    public static void addEvent(String str) throws DukeException {
        if (str.equals("")) {
            throw new IllegalArgsNumException();
        }

        String description = str.split("/")[0].trim();
        if (!str.contains(EVENT_FLAG)) {
            throw new AbsentArgsFlagException();
        }

        String duration = str.split("/")[1];
        Task new_event = new Event(description, duration);
        tasks.add(new_event);
        System.out.println(">>>Added: " + new_event);
        showTaskCount();
    }

    /**
     * Show the number of tasks currently in the taskList.
     */
    public static void showTaskCount() {
        System.out.printf("Now we have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Find the tasks whose description contain the keyword.
     * @param keyword
     */
    public static void findTask(String keyword) {
        ArrayList<Task> task_list_contain_keyword = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                task_list_contain_keyword.add(task);
            }
        }
        if (task_list_contain_keyword.size() == 0) {
            System.out.println("No matching tasks in your list...");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < task_list_contain_keyword.size(); ++i) {
            System.out.print((i + 1) + ".");
            System.out.println(task_list_contain_keyword.get(i));
        }
    }

    private static int isLegalIndex(String index_str) {
        index_str = index_str.trim();
        try {
            int index = Integer.parseInt(index_str);
            if (0 < index && index <= tasks.size()) {
                return index;
            }
        } catch (NumberFormatException e) {
                System.out.println(">>>Pls Enter the Correctly Formatted TaskId!");
                return 0;
            }
        System.out.println(">>>TaskId out of Range!");
        return 0;
    }
}
