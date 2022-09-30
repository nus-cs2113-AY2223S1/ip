package main.java;

import java.util.ArrayList;

/**
 * The taskList class is a class that stores a list of tasks
 * and contains methods that works with that list, being able to
 * add, delete, and get information about the tasks in the list
 */
public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Constructor of a task list if it is ever called upon with no parameters
     */
    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Constructor of a task list from a preexisting set of tasks from a file,
     * or an empty arraylist
     * @param tasks arraylist of tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * The add function adds a task to the end of the list
     * @param task task to be added to list
     */
    public void add(Task task){
        tasks.add(task);
        System.out.println("Got it. I have added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }

    /**
     * Gets the task at the number indicated by the user
     * @param num index of the task on the list you want to see
     * @return Task at the number indicated
     */
    public Task getTask(int num){
        return tasks.get(num);
    }

    /**
     * Returns the number of tasks in the task list
     * @return number of tasks
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Returns the arraylist of tasks if user needs to work with an arraylist instead
     * @return tasklist as an arraylist
     */
    public ArrayList<Task> getAllTasks(){
        return tasks;
    }

    /**
     * Deletes the task from the task list at the indicated number
     * @param num index of task to delete
     */
    public void delete(int num){
        System.out.println("Got it. I have removed this task: ");
        System.out.println(tasks.get(num));
        tasks.remove(num);
        System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }
}
