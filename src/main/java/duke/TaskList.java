package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

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

    private String standardFormatForEachTask(int index) {
        return tasks.get(index).getTaskType() + tasks.get(index).getStatus()
                + tasks.get(index).getDescription() + tasks.get(index).getAddedInfo();
    }
}
