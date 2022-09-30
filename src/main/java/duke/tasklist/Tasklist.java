package duke.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.MissingArgumentException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.data.Storage;
import duke.task.Todo;
import duke.ui.Ui;

public class Tasklist {
    public static ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public static void markTask(int task_no) {
        try {
            Task mark_task = tasks.get(task_no);
            mark_task.setisDone(true);
            Storage.saveTask(tasks);
            Ui.printMarkTask(mark_task);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UnmarkTask(int task_no) {
        try {
            Task unmark_task = tasks.get(task_no);
            unmark_task.setisDone(false);
            Storage.saveTask(tasks);
            Ui.printUnmarkTask(unmark_task);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTodo(String taskDescription) throws MissingArgumentException {
        if (taskDescription == "") {
            throw new MissingArgumentException();
        }
        try {
            Task todo = new Todo(taskDescription);
            tasks.add(todo);
            Storage.saveTask(tasks);
            Ui.printTask(todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDeadline(String description, String by) {

        try {
            Task deadline = new Deadlines(description, by);
            tasks.add(deadline);
            Storage.saveTask(tasks);
            Ui.printTask(deadline);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createEvent(String description, String at) {
        try {
            Task event = new Events(description, at);
            tasks.add(event);
            Storage.saveTask(tasks);
            Ui.printTask(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTask(int task_no) {
        try {
            Task deleteTask = tasks.get(task_no);
            tasks.remove(task_no);
            Storage.saveTask(tasks);
            Ui.printDeletedTask(deleteTask);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findTasks(String keyword) throws InvalidArgumentException {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task: tasks) {
            if(task.getDescription().contains(keyword)) {
                temp.add(task);
            } else {
                throw new InvalidArgumentException();
            }
        }
        Ui.printFoundTask(temp);
    }

}
