package Duke.data;
import java.util.ArrayList;

import Duke.data.exception.DukeException;
import Duke.data.tasks.Task;

public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public void addTasks(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTasks(int taskNumber) throws DukeException {
        if(tasks.size() == 0) {
            throw new DukeException();
        }
        System.out.println("Deleted ");
        printTask(taskNumber);
        tasks.remove(taskNumber);
    }
    public void markDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
    }

    public void unmarkDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
    }
    public void printList() throws DukeException {
        if(tasks.size() == 0) {
            throw new DukeException();
        }
        System.out.println("\tHere are the tasks in your list:");
        for (Task element : tasks) {
            element.printTask();
        }
    }

    public void printTask(int i) {
        tasks.get(i).printTask();
    }

    public String getTaskString(int i) {
        return tasks.get(i).toString();
    }

    public int getSize() {
        return tasks.size();
    }

    public void printSize() {
        System.out.println("\t There are currently " + tasks.size() + " task(s) in the list :)");
    }
}
