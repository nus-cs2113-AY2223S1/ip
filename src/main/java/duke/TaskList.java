package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores the list of tasks.
 */
public class TaskList {

    private static int currentListSize;
    private static ArrayList<Task> taskList;

    /**
     * Constructs constructor for TaskList, create new task arraylist and initializes list size.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        currentListSize = 0;
    }

    /**
     * Retrieves task list size.
     */
    public int getCurrentListSize() {
        return currentListSize;
    }

    /**
     * Sets task list size.
     */
    public void setCurrentListSize(int currentListSize) {
        TaskList.currentListSize = currentListSize;
    }

    /**
     * Retrieves task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Changes specified task to unmarked.
     *
     * @param unmarkTaskIndex Task index of task to be unmarked.
     */
    public void markAsUndone(int unmarkTaskIndex) {
        taskList.get(unmarkTaskIndex).setDone(false);
    }

    /**
     * Changes specified task to marked.
     *
     * @param markTaskIndex Task index of task to be marked.
     */
    public void markAsDone(int markTaskIndex) {
        taskList.get(markTaskIndex).setDone(true);
    }

    /**
     * Deletes specified task and updates task list size.
     *
     * @param deleteTaskIndex Task index of task to be deleted.
     */
    public void deleteTask(int deleteTaskIndex) {
        taskList.remove(deleteTaskIndex);
        currentListSize--;
    }

    /**
     * Adds a todo-type task to task list and updates task list size.
     *
     * @param todoTaskName Name of task to be added.
     */
    public void addToDoTask(String todoTaskName) {
        taskList.add(new Todo(todoTaskName));
        currentListSize++;
    }

    /**
     * Adds a deadline-type task to task list and updates task list size.
     *
     * @param deadlineTaskName Name of task to be added.
     * @param deadlineTaskByDate Date of task to be added.
     * @param deadlineTaskByTime Time of task to be added.
     */
    public void addDeadlineTask(String deadlineTaskName, LocalDate deadlineTaskByDate, String deadlineTaskByTime) {
        taskList.add(new Deadline(deadlineTaskName, deadlineTaskByDate, deadlineTaskByTime));
        currentListSize++;
    }

    /**
     * Adds an event-type task to task list and updates task list size.
     *
     * @param eventTaskName Name of task to be added.
     * @param eventTaskAtDate Date of task to be added.
     * @param eventTaskAtTime Time of task to be added.
     */
    public void addEventTask(String eventTaskName, LocalDate eventTaskAtDate, String eventTaskAtTime) {
        taskList.add(new Event(eventTaskName, eventTaskAtDate, eventTaskAtTime));
        currentListSize++;
    }
}
