package duke.data;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.exception.*;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * <code>TaskList</code> is the class that stores all the tasks added into the application by users.
 * Operations on the task list such as adding, listing, marking and deleting task are defined
 * under this class.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructor of <code>TaskList</code>. Create a new array list for tasks and a new Ui instance.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Another constructor of <code>TaskList</code>. Copy the task array list of the given TaskList
     * object and store it. Also, it creates a new instance of Ui.
     *
     * @param taskList A TaskList object
     */
    public TaskList(TaskList taskList) {
        tasks = taskList.getTasks();
        ui = new Ui();
    }

    /**
     * Return the all the tasks stored in the list.
     *
     * @return An array list storing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Return the number of the tasks stored in the TaskList.
     *
     * @return The size of the array list.
     */
    public int getSize() {
        return this.getTasks().size();
    }

    /**
     * Add a to-do task into the list with a task description
     *
     * @param taskName Description of the task.
     * @return The to-do task object added.
     */
    public Task addTodo(String taskName) {
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        return todo;
    }

    /**
     * Add a deadline task into the list with a task description and deadline time
     *
     * @param taskName     Description of the task.
     * @param deadlineTime Time before the task is to be completed.
     * @return The deadline task object added.
     */
    public Task addDeadline(String taskName, String deadlineTime) {
        Deadline deadline = new Deadline(taskName, deadlineTime);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Add an event task into the list with a task description and event time
     *
     * @param taskName  Description of the task.
     * @param eventTime Time when the task should be completed.
     * @return The event task object added.
     */
    public Task addEvent(String taskName, String eventTime) {
        Event event = new Event(taskName, eventTime);
        tasks.add(event);
        return event;
    }

    /**
     * Return all the task stored in the task list in a formatted output.
     *
     * @return The formatted content of the array list
     */
    public String listTasks() {
        String listContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            listContent += String.format("%d.%s", i + 1, tasks.get(i).getTaskFullDetails());
            listContent += System.lineSeparator();
        }
        listContent += "There are a total of " + tasks.size() + " tasks.";
        return listContent;
    }

    /**
     * Delete the task requested from the task list.
     *
     * @param taskIndex One-based task index
     * @return A string storing the description of the removed task.
     */
    public String deleteTask(int taskIndex) {
        String taskDetail = tasks.get(taskIndex - 1).getTaskFullDetails();
        tasks.remove(taskIndex - 1);
        return taskDetail;
    }

    /**
     * Mark the task requested in the task list as completed.
     * @param taskIndex One-based task index
     * @return The updated task object.
     * @throws DukeException Exception triggered on invalid taskIndex provided.
     */
    public Task markTask(int taskIndex) throws DukeException {
        tasks.get(taskIndex - 1).setDone(true);
        return tasks.get(taskIndex - 1);
    }

    /**
     * Mark the task requested in the task list as not completed.
     * @param taskIndex One-based task index
     * @return The updated task object.
     * @throws DukeException Exception triggered on invalid user input.
     */
    public Task unmarkTask(int taskIndex) throws DukeException {
        tasks.get(taskIndex - 1).setDone(false);
        return tasks.get(taskIndex - 1);
    }
}
