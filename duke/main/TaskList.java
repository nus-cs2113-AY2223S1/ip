package duke.main;
import java.util.ArrayList;
import java.util.Scanner;

import duke.error.DukeException;
import duke.tasks.*;


public class TaskList {
    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static int taskCounter = 0;
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(Scanner fileReader) {
        Parser parser = new Parser();
        while(fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            parser.handleFile(line);
        }
    }

    public TaskList() {

    }

    public static void addDeadline(String name, String date) {
        Deadline task = new Deadline(name, date);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
        Storage.save();
    }
    public static void addEvent(String name, String date) {
        Event task = new Event(name, date);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
        Storage.save();
    }
    public static void addTodo(String name) {
        Todo task = new Todo(name);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
        Storage.save();
    }

    public static void delete(int index) {
        tasks.remove(index);
        Task task = tasks.get(index);
        taskCounter -= 1;
        Ui.deleteTaskMessage(task);
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);
        if (taskCounter == 0) {
            System.out.println("You have no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCounter; ++i) {
                Task task = tasks.get(i);
                System.out.println(task.toString());
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(int index) {
        Task task = tasks.get(index - 1);
        task.isDone = true;
        Ui.markTaskMessage(task);
    }

    public static void unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.isDone = false;
        Ui.unmarkTaskMessage(task);
    }

    public static String findTaskType(Task task) {
        String type = "";
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Event) {
            type = "E";
        } else if (task instanceof Deadline) {
            type = "D";
        }
        return type;
    }

    // getters and setters
    public static int getTaskCounter() {
        return taskCounter;
    }
}
