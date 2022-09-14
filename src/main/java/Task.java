public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String commandArgs) {
        //descriptions = parseDescriptions(commandArgs);
        this.description = commandArgs;
        this.isDone = false;
    }

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
