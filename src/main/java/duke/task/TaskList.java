package duke.task;

import duke.manager.UserInterface;

import java.util.ArrayList;

/**
 * Contains the task list and has operations to add/delete tasks in the list, fetch any task in the list
 * or get the number of tasks in the list.
 */
public class TaskList {

    // array list that stores the tasks
    private static ArrayList<Task> taskList;

    /**
     * Constructor of <code>TaskList</code>.
     * Creates a new array list to store tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a Task object into the ArrayList taskList.
     * Then print a message to the user showing details of the task.
     * Includes ability to suppress message printing.
     *
     * @param newTask the new task being added
     * @param isPrinting boolean parameter to suppress message printing if needed
     */
    public void addTask(Task newTask, boolean isPrinting) {
        taskList.add(newTask);
        if (isPrinting) {
            UserInterface.printAddTaskMessage(newTask);
        }
    }

    /**
     * Deletes a Task object from the ArrayList taskList.
     * Position of Task to be removed is indicated by param taskPosition.
     * Then prints a message showing details of the task.
     *
     * @param taskPosition indicates the position of the Task to delete.
     * @throws IndexOutOfBoundsException If taskPosition < 0 || taskPosition > size of the list
     */
    public static void deleteTask(int taskPosition) throws IndexOutOfBoundsException {

        Task task = get(taskPosition);
        taskList.remove(taskPosition);
        UserInterface.printDeleteTaskMessage(task);
    }

    /**
     * Fetches and returns the task object pointed at by the taskPosition parameter.
     *
     * @param taskPosition position of the task the user chooses
     * @return the task object specified by the taskPosition parameter
     * @throws IndexOutOfBoundsException If taskPosition < 0 || taskPosition > size of the list
     */
    public static Task get(int taskPosition) throws IndexOutOfBoundsException {
        return taskList.get(taskPosition);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public static int getSize() {
        return taskList.size();
    }
}
