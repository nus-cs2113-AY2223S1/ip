package duke.util;

import duke.util.asset.Deadline;
import duke.util.asset.Event;
import duke.util.asset.Task;
import duke.util.asset.Todo;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundException;

import java.util.ArrayList;

public class TaskManager implements Utilities {

    private int taskCount;
    private static ArrayList<Task> tasks;
    private final int INDEX_OFFSET = -1;
    private ArrayList<String> messageBuffer;

    public TaskManager() {
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

    public void findTasksContain(String keyword) {
        int taskMatchCounter = 1;

        for (Task task: tasks) {
            if (task.containsKeyword(keyword)) {
                messageBuffer.add("\t" + (taskMatchCounter + 1) + "." + task.toString());
            }
        }
    }

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

    public void setTask(int taskIndex, boolean isDone) throws TaskNotFoundException {
        final String ERROR_OUT_OF_BOUND = "Sorry, the task does not seem to exist :<";

        if(taskIndex == -1){
            tasks.get(taskCount - 1).setStatus(isDone);
            return;
        }

        if (taskIndex > taskCount || taskIndex < 0) {
            throw new TaskNotFoundException(ERROR_OUT_OF_BOUND);
        }

        taskIndex += INDEX_OFFSET;
        tasks.get(taskIndex).setStatus(isDone);

        if (isDone) {
            messageBuffer.add("\t" + tasks.get(taskIndex).toString());
        } else {
            messageBuffer.add("\t" + tasks.get(taskIndex).toString());
        }

    }

    public void deleteTask(int taskIndex) {

        taskIndex += INDEX_OFFSET;
        messageBuffer.add("\t" + tasks.get(taskIndex).toString());
        tasks.remove(taskIndex);
        taskCount -= 1;

        addSummary();
    }

    public static ArrayList<String> serialize() throws DukeException{
        ArrayList<String> serializedTasks = new ArrayList<>();

        for(Task task: tasks) {
            try {
                serializedTasks.add(task.serialize());

                if(task.getStatus()) {
                    serializedTasks.add("marked");
                }

            } catch (DukeException e) {
                throw new DukeException("error in serializing task");
            }
        }

        return serializedTasks;
    }

    public void clearBuffer() {
        messageBuffer = new ArrayList<>();
    }

    public ArrayList<String> getMessages() {
        return messageBuffer;
    }
}
