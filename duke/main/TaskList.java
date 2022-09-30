package duke.main;
import java.util.ArrayList;
import java.util.Scanner;

import duke.error.DukeException;
import duke.tasks.*;


public class TaskList {
    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static int taskCounter = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(Scanner fileReader) {
        while(fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            Parser.handleFile(line);
        }
    }

    public TaskList() {}

    /**
     * Adds a new deadline task into tasks array
     * @param name name of the deadline task
     * @param date date of the deadline task
     */
    public static void addDeadline(String name, String date) {
        Deadline task = new Deadline(name, date);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
    }

    /**
     * Adds a new event task into tasks array
     * @param name name of the event task
     * @param date date of the event task
     */
    public static void addEvent(String name, String date) {
        Event task = new Event(name, date);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
    }

    /**
     * Adds a new todo task into tasks array
     * @param name name of the todo task
     */
    public static void addTodo(String name) {
        Todo task = new Todo(name);
        tasks.add(task);
        taskCounter += 1;
        Ui.addTaskMessage(task);
    }

    /**
     * Deletes a task from the tasks array
     * @param index index of the task in the array
     */
    public static void delete(int index) {
        tasks.remove(index);
        Task task = tasks.get(index);
        taskCounter -= 1;
        Ui.deleteTaskMessage(task);
    }

    /**
     * Lists all tasks in tasks array
     * Shows a message if there are no tasks in the tasks array
     */
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

    /**
     * Marks a task in the task array as done
     * @param index index of the task to be marked as done
     */
    public static void markTask(int index) {
        Task task = tasks.get(index - 1);
        task.isDone = true;
        Ui.markTaskMessage(task);
    }

    /**
     * Marks a task in the tasks array as not done
     * @param index index of the task to be marked as not done
     */
    public static void unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.isDone = false;
        Ui.unmarkTaskMessage(task);
    }

    /**
     * Finds the category of the task
     * Whether it is a deadline, event or todo task
     * @param task the task whose type will be found
     * @return the type of the task (T, E, or D)
     */
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

    /**
     * Finds tasks from tasks array that contains the keyword
     * @param keyword keyword input from the user
     */
    public static void find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskName = task.getName();
            if (taskName.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        Ui.printTasks(matchingTasks);
    }

    // getters and setters
    public static int getTaskCounter() {
        return taskCounter;
    }
}
