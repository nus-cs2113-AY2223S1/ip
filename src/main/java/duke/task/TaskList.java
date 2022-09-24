package duke.task;

import duke.exception.AbsentArgsFlagException;
import duke.exception.DukeException;
import duke.exception.IllegalArgsNumException;
import duke.exception.IllegalArgsTypeException;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String DEADLINE_FLAG = "/by";
    private static final String EVENT_FLAG = "/at";
    private static final String[] ILLEGAL_ARGS = {"blah"};

    public TaskList(Storage storage) {
        // TODO: Init tasks with storage.loadtask
    }

    private static void addTask(String taskDescription) throws IllegalArgsTypeException {
        if (Arrays.asList(ILLEGAL_ARGS).contains(taskDescription)) {
            throw new IllegalArgsTypeException();
        }
        Task new_task = new Task(taskDescription);
        tasks.add(new_task);
        System.out.println(">>>Added: " + new_task);
        // TODO: dumpTask(new_task) outside
        showTaskCount();
    }

    private static void listTask() {
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

    private static void markTask(int taskId, boolean setMark) {
        if (taskId <= 0 || taskId > tasks.size()) {
            System.out.println(">>>Pls Enter the Right TaskId!");
        }
        else {
            tasks.get(taskId-1).setMarked(setMark);
            if (setMark) {
                System.out.println(">>>Nice! I've marked this task as done:");
            }
            else {
                System.out.println(">>>OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(taskId-1));
        }
    }

    private static void deleteTask(int num) {
        if (num <= 0 || num > tasks.size()) return;
        Task tmp_task = tasks.get(num - 1);
        tasks.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(">>>" + tmp_task);
        showTaskCount();
    }



    private static void addTodo(String todoDescription) throws IllegalArgsNumException {
        if (todoDescription.equals("")) {
            throw new IllegalArgsNumException();
        }
        Task new_todo = new Todo(todoDescription);
        tasks.add(new_todo);
        System.out.println(">>>Added: " + new_todo);
        showTaskCount();
    }

    private static void addDeadline(String str) throws DukeException {
        if (str.equals("")) {
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

    private static void addEvent(String str) throws DukeException {
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

    private static void showTaskCount() {
        System.out.printf("Now we have %d tasks in the list.\n", tasks.size());
    }


}
