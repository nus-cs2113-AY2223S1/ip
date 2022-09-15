package Tasks;

public class Task{
    protected String taskTitle;
    protected boolean taskDone;
    protected String taskType;
    protected String taskDate;
    protected String taskDeadline;

    public Task(String taskTitle){ // constructor
        this.taskTitle = taskTitle;
        this.taskDone = false;
    }

    public Task(String taskTitle, boolean taskDone){ // constructor with taskDone
        this.taskTitle = taskTitle;
        this.taskDone = taskDone;
    }

    //get methods
    public String getTaskTitle(){
        return this.taskTitle;
    }

    public boolean getTaskDone(){
        return this.taskDone;
    }


    public String getTaskType(){
        return this.taskType;
    }

    public String getTaskDate(){
        return this.taskDate;
    }

    public String getTaskDeadline(){
        return this.taskDeadline;
    }

    //set methods
    public void setTaskDone(boolean done){
        this.taskDone = done;
    }

    public void setTaskType(String taskType){
        this.taskType = taskType;
    }

    public void setTaskDate(String taskDate){
        this.taskDate = taskDate;
    }

    public void setTaskDeadline(String taskDeadline){
        this.taskDeadline = taskDeadline;
    }
}