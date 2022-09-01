public class Task {
    /** Represent the individual task that the user input */
    protected String description;
    /** To check if the task is mark as done or not for each individual task */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusOfDone() {
        //mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getStatusOfTypeTask() { return " "; }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public void markTypeTask() {}

    public String displayTypeTaskDetails() {
        String typeTaskParameter = description;
        boolean isEventOrDeadline = description.contains("/at") || description.contains("/by");
        if(isEventOrDeadline) {
            int indexOfDash = description.indexOf("/");
            String typeTaskTitle = typeTaskParameter.substring(0, indexOfDash);
            String typeTaskDetail = typeTaskParameter.substring(indexOfDash);
            typeTaskDetail = typeTaskDetail.replaceFirst("/", "(");
            typeTaskDetail = typeTaskDetail.substring(0,3) + ':' + typeTaskDetail.substring(3) + ")";
            typeTaskParameter = typeTaskTitle + typeTaskDetail;
        }
        return typeTaskParameter;
    }
}
