package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.ArrayList;

public class TaskList {

    private static int currentListSize;
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
        currentListSize = 0;
    }

    public int getCurrentListSize() {
        return currentListSize;
    }

    public void setCurrentListSize(int currentListSize) {
        TaskList.currentListSize = currentListSize;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void markAsUndone(int unmarkTaskIndex) {
        taskList.get(unmarkTaskIndex).setDone(false);
    }

    public void markAsDone(int markTaskIndex) {
        taskList.get(markTaskIndex).setDone(true);
    }

    public void deleteTask(int deleteTaskIndex) {
        taskList.remove(deleteTaskIndex);
        currentListSize--;
    }

    public void addToDoTask(String todoTaskName) {
        taskList.add(new Todo(todoTaskName));
        currentListSize++;
    }

    public void addDeadlineTask(String deadlineTaskName, String deadlineTaskBy) {
        taskList.add(new Deadline(deadlineTaskName, deadlineTaskBy));
        currentListSize++;
    }

    public void addEventTask(String eventTaskName, String eventTaskAt) {
        taskList.add(new Event(eventTaskName, eventTaskAt));
        currentListSize++;
    }
}
