package main.java;

public class Task {
    private String name;
    private boolean isDone;

    public Task() {
        name = "";
        isDone = false;
    }

    public Task(String n, boolean d) {
        name = n;
        isDone = d;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean d) {
        if (isDone == d) {
            System.out.println("Task was already marked like that");
        } else {
            isDone = !isDone;
        }
    }

    public boolean getDone(){
        return isDone;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
    public String classInfo(){
        return "Task";
    }
    public String getDate(){
        return "";
    }
}
