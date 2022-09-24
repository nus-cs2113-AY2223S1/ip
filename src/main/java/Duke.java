import duke.exception.AbsentArgsFlagException;
import duke.exception.DukeException;
import duke.exception.IllegalArgsNumException;
import duke.exception.IllegalArgsTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.nio.Buffer;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        showLogo();
        showWelcomeMsg();
        readUserCmd();
        showGoodbyeMsg();
    }



    private static final String END_CMD = "bye";
    private static final String DEADLINE_FLAG = "/by";
    private static final String EVENT_FLAG = "/at";
    private static final String[] ILLEGAL_ARGS = {"blah"};
    private static final int MAX_TASK = 100;

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String DUKE_DUMP_FILE = "duke.txt";




    private static void showTaskCount() {
        System.out.printf("Now we have %d tasks in the list.\n", tasks.size());
        showSeparator();
    }

    private static void readUserCmd() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(END_CMD)) {
            String[] args = input.split(" ", 2);
            String arg1 = args[0];
            String arg2 = "";
            if (args.length > 1) {
                arg2 = args[1];
            }
            switch (arg1) {
            case "list":
                listTask();
                break;
            case "mark":
                markTask(Integer.parseInt(arg2),
                        true);
                break;
            case "unmark":
                markTask(Integer.parseInt(arg2),
                        false);
                break;
            case "todo":
                try {
                    addTodo(arg2);
                } catch (DukeException e) {
                    handleAddTodoException();
                }
                break;
            case "deadline":
                try {
                    addDeadline(arg2);
                } catch (DukeException e) {
                    handleAddDeadException(e);
                }
                break;
            case "event":
                try {
                    addEvent(arg2);
                } catch (DukeException e) {
                    handleAddEventException(e);
                }
                break;
            case "delete":
                deleteTask(Integer.valueOf(arg2));
                break;
            default:
                try {
                    addTask(input);
                } catch (IllegalArgsTypeException e) {
                    handleAddTaskException();
                }
            }
            input = sc.nextLine();
        }
    }

    private static void addTask(String taskDescription) throws IllegalArgsTypeException {
        if (Arrays.asList(ILLEGAL_ARGS).contains(taskDescription)) {
            throw new IllegalArgsTypeException();
        }
        Task new_task = new Task(taskDescription);
        tasks.add(new_task);
        System.out.println(">>>Added: " + new_task);
        dumpTask(new_task);
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
        showSeparator();
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
        showSeparator();
    }

    private static void deleteTask(int num) {
        if (num <= 0 || num > tasks.size()) return;
        Task tmp_task = tasks.get(num - 1);
        tasks.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(">>>" + tmp_task);
        showTaskCount();
    }

    private static void dumpTask(Task task) {
        try {
            File file_name = new File(DUKE_DUMP_FILE);
            file_name.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file_name));
            out.write(String.valueOf(task) + "\r\n");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private static void handleAddTaskException() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        showSeparator();

    }
    private static void handleAddTodoException() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        showSeparator();
    }

    private static void handleAddDeadException(DukeException e) {
        if (e instanceof IllegalArgsNumException) {
            System.out.println("OOPS!!! The description of a DDL cannot be empty.");
        }
        else if (e instanceof AbsentArgsFlagException) {
            System.out.println("OOPS!!! The description of a DDL should contain /by.");
        }
        showSeparator();
    }

    private static void handleAddEventException(DukeException e) {
        if (e instanceof IllegalArgsNumException) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        }
        else if (e instanceof AbsentArgsFlagException) {
            System.out.println("OOPS!!! The description of a event should contain /at.");
        }
        showSeparator();
    }
}
