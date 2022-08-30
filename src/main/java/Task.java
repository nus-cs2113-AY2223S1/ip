public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public boolean markAsDone(){
        isDone = true;
        return isDone;
    }
    public boolean unmarkAsNotDone(){
        isDone = false;
        return isDone;
    }
}
