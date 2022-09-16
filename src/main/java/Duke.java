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

    private static void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    private static final String END_CMD = "bye";
    private static final String DEADLINE_FLAG = "/by";
    private static final String EVENT_FLAG = "/at";
    private static final String[] ILLEGAL_ARGS = {"blah"};
    private static final int MAX_TASK = 100;
    private static final int SEPARATOR_LEN = 50;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASK];
    private static final String DUKE_DUMP_FILE = "duke.txt";

    private static void showSeparator() {
        String separator = String.join("", Collections.nCopies(SEPARATOR_LEN, "="));
        System.out.println(separator);
    }

    private static void showWelcomeMsg() {
        showSeparator();
        System.out.println("Hello! I'm Duke ^_^");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    private static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon! qwq");
        showSeparator();
    }

    private static void showTaskCount() {
        System.out.printf("Now we have %d tasks in the list.\n", taskCount);
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
        tasks[taskCount] = new Task(taskDescription);
        dumpTask(tasks[taskCount]);
        System.out.println(">>>Added: " + tasks[taskCount++]);

        showTaskCount();
    }

    private static void listTask() {
        if (taskCount == 0) {
            System.out.println(">>>No Current Tasks.");
        } else {
            System.out.println(">>>Current Tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.print(">>>" + (i + 1) + ".");
                System.out.println(tasks[i]);
            }
        }
        showSeparator();
    }

    private static void markTask(int taskId, boolean setMark) {
        if (taskId <= 0 || taskId > taskCount) {
            System.out.println(">>>Pls Enter the Right TaskId!");
        }
        else {
            tasks[taskId-1].setMarked(setMark);
            if (setMark) {
                System.out.println(">>>Nice! I've marked this task as done:");
            }
            else {
                System.out.println(">>>OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks[taskId-1]);
        }
        showSeparator();
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
        tasks[taskCount] = new Todo(todoDescription);
        System.out.println(">>>Added: " + tasks[taskCount++]);
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
        tasks[taskCount] = new Deadline(description, by);
        System.out.println(">>>Added: " + tasks[taskCount++]);
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
        tasks[taskCount] = new Event(description, duration);
        System.out.println(">>>Added: " + tasks[taskCount++]);
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
