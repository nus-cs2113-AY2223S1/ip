package TaskManager;

public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskType(){
        return taskType;
    }

    public void setTaskType(String type){
        taskType = type;
    }

    public void markAsDone() {
        if (isDone==false) this.isDone = true;
    }

    public void markAsNotDone(){
        if (isDone==true) this.isDone = false;
    }

    public String getDescription(){
        return description;
    }

    public String getDueDate(){
        return " ";
    }
    public String getDateTime(){
        return " ";
    }
}