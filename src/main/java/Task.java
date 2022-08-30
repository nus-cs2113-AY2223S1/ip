public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String commandArgs) {
        //descriptions = parseDescriptions(commandArgs);
        this.description = commandArgs;
        this.isDone = false;
    }

    public void setStringState(Boolean state) {
        this.isDone = state;
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
