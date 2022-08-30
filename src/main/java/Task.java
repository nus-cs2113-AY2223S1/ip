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

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskType() {
        return "";
    }

    private String assembleResponse() {
        String response = "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        return response;
    }

    public String getResponse() {
        return this.assembleResponse();
    }
}
