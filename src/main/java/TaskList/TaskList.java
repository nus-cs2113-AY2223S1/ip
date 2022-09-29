package TaskList;

import Tasks.Deadline;
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

    /**
     * Adds a todo task to the task list, including the todo name and status.
     *
     * @param todoName the name of the todo task
     * @param status indicates whether the todo task is done or not
     */
    public static void addTodo(String todoName, boolean status) {
        list.add(new ToDo(todoName, status));
    }

    public static void addEvent(String eventName, String eventTime) {
        list.add(new Event(eventName, eventTime));
    }

    /**
     * Adds an event task to the task list, including the event name, status and time.
     *
     * @param eventName the name of the event task
     * @param status indicates whether the event is done or not
     * @param eventTime the time associated with the event
     */
    public static void addEvent(String eventName, boolean status, String eventTime) {
        list.add(new Event(eventName, status, eventTime));
    }

    public static void addDeadline(String taskName, String taskDate) {
        list.add(new Deadline(taskName, taskDate));
    }

    /**
     * Adds a deadline task to the task list, including the deadline name, status and date.
     *
     * @param taskName the name of the deadline task
     * @param status indicates whether the deadline is done or not
     * @param taskDate the date associated with the deadline
     */
    public static void addDeadline(String taskName, boolean status, String taskDate) {
        list.add(new Deadline(taskName, status, taskDate));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the position of the task to be deleted in the task list
     */
    public static void deleteTask(int index) {
        list.remove(index);
    }
}
