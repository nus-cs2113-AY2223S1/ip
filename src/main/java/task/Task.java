package task;

/**
 * parent class of todo, deadline, and event
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * used when only description is given
     *
     * @param description description of the task
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    /**
     * used when both the description and isDone status are given
     *
     * @param description description of the task
     * @param isDone the status of the task
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
    }

    /**
     * print the task in the following format
     * e.g. [T][X] read book
     */
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
