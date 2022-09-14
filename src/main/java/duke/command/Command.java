package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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

    public static void printTaskList(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
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
        int eventId = Task.getNumberOfTasks() - 1;
        printNewTask(tasks.get(eventId));
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
        int deadlineId = Task.getNumberOfTasks() - 1;
        printNewTask(tasks.get(deadlineId));
    }

    static void tryAddTodo(ArrayList<Task> tasks, String line) {
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
        int todoId = Todo.getNumberOfTasks() - 1;
        printNewTask(tasks.get(todoId));
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

    public static void readFile(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int numberOfLine = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parsedLine = line.split(" \\| ");
            numberOfLine += 1;
            switch (parsedLine[0]) {
            case "T":
                tryAddTodo(tasks, "todo " + parsedLine[2]);
                break;
            case "D":
                String deadline = "deadline " + parsedLine[2] + " /by " + parsedLine[3];
                tryAddDeadline(tasks, deadline);
                break;
            case "E":
                String event = "event " + parsedLine[2] + " /at " + parsedLine[3];
                System.out.println(event);
                tryAddEvent(tasks, event);
                break;
            }
            switch (parsedLine[1]) {
            case "1":
                String mark = "mark " + numberOfLine;
                tryMarkTask(tasks, mark);
                break;
            case "0":
                String unmark = "unmark " + numberOfLine;
                tryUnmarkTask(tasks, unmark);
                break;
            }
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            String line = "";
            switch (tasks.get(i).getClass().getSimpleName()) {
            case "Todo":
                line = line + "T | ";
                break;
            case "Deadline":
                line = line + "D | ";
                break;
            case "Event":
                line = line + "E | ";
                break;
            }
            switch (tasks.get(i).getStatusIcon()) {
            case "X":
                line = line + "1 | ";
                break;
            case " ":
                line = line + "0 | ";
                break;
            }
            line = line + tasks.get(i).getDescription();
            switch (tasks.get(i).getClass().getSimpleName()) {
            case "Deadline":
                Deadline d = (Deadline) tasks.get(i);
                line = line + " | " + d.getBy();
                break;
            case "Event":
                Event e = (Event) tasks.get(i);
                line = line + " | " + e.getAt();
                break;
            }
            line = line + System.lineSeparator();
            fw.write(line);
        }
        fw.close();
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
