package duke.task;

import duke.task.Task;
import duke.manager.UserInterface;

import java.util.ArrayList;

public class TaskList {

    static ArrayList<Task> taskList;

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
        UserInterface.printAddTaskMessage(taskList, newTask);
    }

    /**
     * Deletes a Task object from the ArrayList taskList.
     * Position of Task to be removed is indicated by param taskPosition.
     *
     * @param taskPosition indicates the position of the Task to delete.
     */
    public static void deleteTask(int taskPosition) {
        if (taskPosition < 0) {
            System.out.println("Please give me a positive number instead!");
            return;
        }   else if (taskPosition < taskList.size()) {
            System.out.println("☹ OOPS!!! You don't have that many tasks!");
            return;
        }
        UserInterface.printDeleteTaskMessage(taskList, taskList.get(taskPosition));
        taskList.remove(taskPosition);
    }

    public static Task get(int taskPosition) {
        Task task = taskList.get(taskPosition);
        return task;
    }

    public static int getSize() {
        return taskList.size();
    }
}
