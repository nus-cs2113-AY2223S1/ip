package task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
    }

    public String toString(){
        String output = "[ ] ";
        if(isDone){
            output = "[X] ";
        }

        return output + this.description;
    }

    public void setDone(boolean status){
        isDone = status;
    }

    public boolean getDone(){
        return isDone;
    }

    public String getDescription(){
        return description;
    }

}
