package duke.task;

import duke.manager.UserInterface;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a Task object into the ArrayList taskList.
     *
     * @param newTask the new task being added
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        UserInterface.printAddTaskMessage(newTask);
    }

    /**
     * Deletes a Task object from the ArrayList taskList.
     * Position of Task to be removed is indicated by param taskPosition.
     *
     * @param taskPosition indicates the position of the Task to delete.
     */
    public static void deleteTask(int taskPosition) {

        Task task = get(taskPosition);
        taskList.remove(taskPosition);
        UserInterface.printDeleteTaskMessage(task);
    }

    public static Task get(int taskPosition) {
        return taskList.get(taskPosition);
    }

    public static int getSize() {
        return taskList.size();
    }
}
