package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Adds a Task object into the ArrayList taskList.
     *
     * @param newTask the new task being added
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Deletes a Task object from the ArrayList taskList.
     * Position of Task to be removed is indicated by param taskPosition.
     *
     * @param taskPosition indicates the position of the Task to delete.
     */
    public void deleteTask(int taskPosition) {
        taskList.remove(taskPosition);
    }


}
