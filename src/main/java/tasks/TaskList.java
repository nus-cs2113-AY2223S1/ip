package tasks;

import java.util.ArrayList;

import exceptions.InvalidTaskNumberException;
import ui.Messages;
import ui.Ui;

/**
 * Stores the TaskList class, its attributes and methods. Stores objects of the Task class and its children in an ArrayList.
 */
public class TaskList {
    public final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public ArrayList<Task> getTaskList() { return tasks; }

    private boolean isValidTaskNumber(int taskIndex) throws InvalidTaskNumberException {
        boolean isValid = (taskIndex > -1 && taskIndex < tasks.size());
        if (!isValid) {
            throw new InvalidTaskNumberException();
        }
        return isValid;
    }

    public void addTask(Task task){
        tasks.add(task);
        Messages.addMessage();
        System.out.println(task + "\n");
        Ui.border();
    }

    public void markTask(int taskIndex) throws InvalidTaskNumberException {
        if (isValidTaskNumber(taskIndex))
            tasks.get(taskIndex).setMarked();
    }

    public void unmarkTask(int taskIndex) throws InvalidTaskNumberException {
        if (isValidTaskNumber(taskIndex))
            tasks.get(taskIndex).setUnmarked();
    }

    public void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (isValidTaskNumber(taskIndex)) {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            Messages.defaultDeleteMessage();
            System.out.println(task + "\n");
            Ui.border();
        }
    }

    public void printList(){
        if (tasks.size()>0){
            Ui.border();
            System.out.println("Here are the tasks in your list:" + "\n");
            for (int i = 0; i < tasks.size(); i++){
                System.out.println((i+1) + ". " + tasks.get(i) + "\n");
            }
            Ui.border();
        }else{
            System.out.println("There are no tasks in the list" + "\n");
        }
    }

    public void countTasks() {
        System.out.println("Now you have " );
        System.out.println(tasks.size());
        System.out.println(" tasks in the list.");
        Ui.border();
    }
}
