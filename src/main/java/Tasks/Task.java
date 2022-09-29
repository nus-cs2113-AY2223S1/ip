package Tasks;

public class Task {
    private String taskName = "";
    private String taskType = "";
    private String taskDeadline = "";
    private String eventTime = "";
    private boolean isDone;

    public Task() {
        this.taskName = "";
        this.isDone = false;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.isDone = status;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Checks whether a task is marked as done.
     *
     * @return true if tasked is marked as done, or false if task is not marked as done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets a task as done or not done, using a boolean variable of true or false respectively.
     *
     * @param done if this parameter is true, the task is marked as done. If it is false, the task is marked as not
     *             done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Sets the task type as todo, event or deadline.
     *
     * @param taskType the type of task i.e. todo, event or deadline
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
