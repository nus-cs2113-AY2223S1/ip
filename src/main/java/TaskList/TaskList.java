package TaskList;

import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public static void addTodo(String todoName) {
        list.add(new ToDo(todoName));
    }

    public static void addTodo(String todoName, boolean status) {
        list.add(new ToDo(todoName, status));
    }

    public static void addEvent(String eventName, String eventTime) {
        list.add(new Event(eventName, eventTime));
    }

    public static void addEvent(String eventName, boolean status, String eventTime) {
        list.add(new Event(eventName, status, eventTime));
    }

    public static void addDeadline(String taskName, String taskDate) {
        list.add(new Event(taskName, taskDate));
    }

    public static void addDeadline(String taskName, boolean status, String taskDate) {
        list.add(new Event(taskName, status, taskDate));
    }

    public static void deleteTask(int index) {
        list.remove(index);
    }
}
