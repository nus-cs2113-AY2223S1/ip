public class Task {
    private boolean done;
    private String taskDescription;

    public Task(){
        this("");
    }

    public Task(String description){
        this.taskDescription = description;
        this.done = false;
    }

    public String getTask(){
        return this.taskDescription;
    }

    public void setDone(boolean isDone){
        this.done = isDone;
    }

    public boolean getDone(){
        return this.done;
    }
}
