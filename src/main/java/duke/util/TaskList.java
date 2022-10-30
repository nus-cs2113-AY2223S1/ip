package duke.util;

import duke.exception.TaskCheckedException;
import duke.util.asset.Task;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundException;

import java.util.ArrayList;

/**
 * A collection to store the various tasks
 */
public class TaskList implements Utilities {

    private int taskCount;
    private static ArrayList<Task> tasks;
    private ArrayList<String> messageBuffer;
    private final int INDEX_OFFSET = -1;
    private final String ERROR_OUT_OF_BOUND = "Sorry, the task does not seem to exist :<";

    public TaskList() {
        taskCount = 0;
        tasks = new ArrayList<>();
        messageBuffer = new ArrayList<>();
    }

    public void close() {
        taskCount = 0;
        tasks.clear();
        messageBuffer.clear();
    }

    private void addSummary() {
        messageBuffer.add("Beep boop, now you have " + taskCount + " tasks");
    }

    public void clearAllTask() {
        taskCount = 0;
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getAllTask() {
        return tasks;
    }

    public void generateTaskList() {
        if (taskCount == 0) {
            messageBuffer.add("You have no upcoming task! Wanna add some?");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            messageBuffer.add("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Search for all the tasks that contains the search keyword and append to message buffer
     * @param keyword the keyword to use for searching
     */
    public void findTasksContain(String keyword) {
        int taskMatchCounter = 1;

        for (Task task: tasks) {
            if (task.containsKeyword(keyword)) {
                messageBuffer.add("\t" + (taskMatchCounter) + "." + task.toString());
            }
            taskMatchCounter += 1;
        }
    }

    /**
     * Add a task by task index
     *
     * @param taskToAdd the task to be appended to the tasklist
     */
    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
        taskCount += 1;

        try {
            messageBuffer.add(taskToAdd.getAddMessage());
        } catch (DukeException e) {
            messageBuffer.add(e.getErrorMessage());
        }

        messageBuffer.add("\t" + tasks.get(taskCount - 1).toString());
        addSummary();

    }

    /**
     * Set a task as marked or unmarked by task index
     *
     * @param taskIndex the index for the task as seen by user.
     * @param isDone the desired state to be marked for the task of interest
     * @throws TaskNotFoundException if the taskIndex is out of bound
     */
    public void setTask(int taskIndex, boolean isDone) throws TaskNotFoundException, TaskCheckedException {

        if (taskIndex > taskCount || taskIndex <= 0) {
            throw new TaskNotFoundException(ERROR_OUT_OF_BOUND);
        }

        taskIndex += INDEX_OFFSET;
        if (tasks.get(taskIndex).getStatus() == isDone) {
            String errorMessage;
            if (isDone) {
                errorMessage = "The task is already marked :O";
            } else {
                errorMessage = "The task is already unmarked :O";
            }
            throw new TaskCheckedException(errorMessage);
        }
        tasks.get(taskIndex).setStatus(isDone);

        messageBuffer.add("\t" + tasks.get(taskIndex).toString());
    }

    /**
     * Set the last task in the list as complete or incomplete
     *
     * @param isDone the desired state to be marked for the task of interest
     * @throws DukeException if setTask throws exception
     */
    public void setLastTask(boolean isDone) throws DukeException {
        try {
            setTask(taskCount, isDone);
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Deletes a task by task index
     *
     * @param taskIndex the index for the task as seen by user.
     */
    public void deleteTask(int taskIndex) throws TaskNotFoundException {

        if (taskIndex > taskCount || taskIndex <= 0) {
            throw new TaskNotFoundException(ERROR_OUT_OF_BOUND);
        }

        taskIndex += INDEX_OFFSET;
        messageBuffer.add("\t" + tasks.get(taskIndex).toString());
        tasks.remove(taskIndex);
        taskCount -= 1;

        addSummary();
    }

    /**
     * Returns a list of serialized tasks.
     * Append "marked" to a task if the task is marked at programme exit so that it can be restored next session
     *
     * @return List of serialized tasks
     * @throws DukeException If task parent class is being serialized.
     */
    public static ArrayList<String> serialize() throws DukeException {
        ArrayList<String> serializedTasks = new ArrayList<>();

        for(Task task: tasks) {
            try {
                serializedTasks.add(task.serialize());

                if (task.getStatus()) {
                    serializedTasks.add("marked");
                }

            } catch (DukeException e) {
                throw new DukeException("Error in serializing task");
            }
        }

        return serializedTasks;
    }

    /**
     * Clear the messageBuffer before new messages from next commands are added
     */
    public void clearBuffer() {
        messageBuffer = new ArrayList<>();
    }

    public ArrayList<String> getMessages() {
        return messageBuffer;
    }
}
