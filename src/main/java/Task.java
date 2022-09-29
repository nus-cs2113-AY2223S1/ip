/**
 * Defines The basic task type, which has a description and its completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
    * Initializes a task with a user input
    * @param commandArgs user Input
    */
    public Task(String commandArgs) {
        this.description = commandArgs;
        this.isDone = false;
    }

    /**
    * Initializes a task with a desciption and its completion status
    * @param description the description of the task
    * @param isDone the completion status
    */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public void setStringState(Boolean state) {
        this.isDone = state;
    }

    public String getStorageFormat(){
        String status = isDone ? "1" : "0";
        return getTaskType()+" | "+status+" | "+getDescription();
    }

    protected String getDescription() {
        return this.description;
    }

    protected String getResponse() {
        return this.assembleResponse();
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getTaskType() {
        return "";
    }

    protected String assembleResponse() {
        String response = "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        return response;
    } 
}
