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

    public void setDone(boolean a) {
        if (isDone == a) {
            System.out.println("Task was already marked like that");
        } else {
            isDone = !isDone;
        }
    }

    public void printTask() {
        if (isDone) {
            System.out.println("[X] " + name);
        } else {
            System.out.println("[ ] " + name);
        }
    }
}
