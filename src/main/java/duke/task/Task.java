package duke.task;

public abstract class Task {
    /** Represent the individual task that the user input */
    protected String description;
    /** To check if the task is mark as done or not for each individual task */
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusOfDone() {
        String status = " ";
        if (isDone) {
            status = "X";
        }
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public abstract String getStatusOfTypeTask();

    public abstract void markTypeTask();

    /**
     * Returns a string to properly display the event or deadline task information
     *
     * @return typeTaskParameter modified string to contain the event or deadline task information
     */
    public String displayTypeTaskDetails() {
        String typeTaskParameter = description;
        boolean isEventOrDeadline = description.contains("/at") || description.contains("/by");
        if (isEventOrDeadline) {
            typeTaskParameter = modifyDescription(typeTaskParameter);
        }
        return typeTaskParameter;
    }

    /**
     * Modifies the given description by the User
     *
     * @param typeTaskParameter string that is taken from description
     * @return typeTaskParameter a modified string
     */
    public String modifyDescription(String typeTaskParameter) {
        int indexOfDash = description.indexOf("/");
        String typeTaskTitle = typeTaskParameter.substring(0, indexOfDash);
        String typeTaskDetail = typeTaskParameter.substring(indexOfDash);
        typeTaskDetail = typeTaskDetail.replaceFirst("/", "(");
        typeTaskDetail = typeTaskDetail.substring(0,3) + ':' + typeTaskDetail.substring(3) + ")";
        typeTaskParameter = typeTaskTitle + typeTaskDetail;
        return typeTaskParameter;
    }
}
