package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * Represent the array list containing all the tasks inputted by the user and from historical inputs.
 * Tasks can be either a Todo, Deadline, or Event object.
 * Contains the possible actions to be done to the task list such as addition, deletion or edition of its status.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    /**
     * To add the historical data into the task list when the program starts running.
     *
     * @param storage the file where data will be loaded from
     */
    public void loadData(Storage storage) {
        storage.load(tasks);
        taskCount = tasks.size();
    }

    public void addTodo(Todo t, Storage storage ) {
        tasks.add(t);
        storage.addToFile(tasks.get(taskCount), taskCount);
        showAdditionMessage();
    }

    public void addDeadline(Deadline d, Storage storage) {
        tasks.add(d);
        storage.addToFile(tasks.get(taskCount), taskCount);
        showAdditionMessage();
    }

    public void addEvent(Event e, Storage storage) {
        tasks.add(e);
        storage.addToFile(tasks.get(taskCount), taskCount);
        showAdditionMessage();
    }

    private void showAdditionMessage() {
        System.out.println("\tAdded:");
        System.out.println("\t  " + standardFormatForEachTask(taskCount));
        taskCount++;
        System.out.println("\tNow you have " + taskCount + " tasks in the list");
    }

    public void list() {
        System.out.println("\tThese are the tasks in your list:");
        int numbering = 1;
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println("\t" + numbering + "." + standardFormatForEachTask(i));
            numbering++;
        }
    }

    public void markingAction(int index, boolean isDone, Storage storage) throws IndexOutOfBoundsException {
        checkIndexValidity(index + 1);
        tasks.get(index).setCompletion(isDone);
        showMarkingMessage(index);
        storage.updateFile(tasks);
    }

    private void showMarkingMessage(int index) {
        System.out.println("\tNoted, the following task is set as requested:");
        System.out.println("\t  " + standardFormatForEachTask(index));
    }

    public void delete(int index, Storage storage) throws IndexOutOfBoundsException {
        checkIndexValidity(index + 1);
        showDeleteMessage(index);
        tasks.remove(index);
        storage.updateFile(tasks);
    }

    public void showDeleteMessage(int index) {
        System.out.println("\tNoted. I have removed this task:");
        System.out.println("\t  " + standardFormatForEachTask(index));
        taskCount--;
        System.out.println("\tNow you have " + taskCount + " tasks in the list");
    }

    public void checkIndexValidity(int index) throws IndexOutOfBoundsException {
        if (taskCount < index) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Searching for tasks with description containing the wanted string by iterating through all tasks in task list.
     * Printing out the task if the description contains the wanted string.
     *
     * @param content the wanted string in task description inputted by the user
     */
    public void find(String content) {
        System.out.println("\tBelow are the tasks with the content specified:");
        for (Task t : tasks) {
            if (t.getDescription().contains(content)) {
                int index = tasks.indexOf(t);
                System.out.println("\t " + standardFormatForEachTask(index));
            }
        }
    }

    /**
     * Returning a string in format of [TYPE][STATUS]DESCRIPTION+ADDED_INFO
     *
     * @param index the index of task in task list
     * @return the task in standardised format
     */
    private String standardFormatForEachTask(int index) {
        return tasks.get(index).getTaskType() + tasks.get(index).getStatus()
                + tasks.get(index).getDescription() + tasks.get(index).getAddedInfo();
    }
}
