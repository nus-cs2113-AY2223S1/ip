package duke;

public class Task {
    protected String name;
    static public int currTotal = 0;
    public int index;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.index = currTotal + 1;
        currTotal += 1;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void UpdateStatus(){
        this.isDone = !this.isDone;
    }

    public static void countRemainingTasks(){
        System.out.println("Now you have "+currTotal+" tasks in the list");
    }
    //...
    public void UpdateRemoval(){
        this.index -=1;
    }
    public static void removeTask(Task task){
        currTotal -=1;

    }
    public String toString(){
        return ( this.index + "."+"["+this.getStatusIcon()+"] " + this.name);
    }

}