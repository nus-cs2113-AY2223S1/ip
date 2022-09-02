public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }
    public boolean isDone(boolean isDone){
        return this.isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void updateStatus(boolean status){
        this.isDone = status;
    }
    public char getTaskType(){
        return this.taskType;
    }
    public void printTask(){
        System.out.print("["+getTaskType()+"]["+getStatusIcon()+"] "+description);
    }
}