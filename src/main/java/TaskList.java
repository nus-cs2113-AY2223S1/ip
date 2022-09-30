package main.java;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    public TaskList(){
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public void add(Task t){
        tasks.add(t);
        System.out.println("Got it. I have added this task: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }
    public Task getTask(int num){
        return tasks.get(num);
    }
    public int size(){
        return tasks.size();
    }
    public ArrayList<Task> getAllTasks(){
        return tasks;
    }
    public void delete(int num){
        System.out.println("Got it. I have removed this task: ");
        System.out.println(tasks.get(num));
        tasks.remove(num);
        System.out.println("Now you have "+tasks.size()+" tasks in your list.");
    }
}
