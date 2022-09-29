public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String CHECK_MARK = "[X]";
    private static final String UNCHECK_MARK = "[]";
    protected static final TaskType taskType = TaskType.TASK;
    protected String time;

    /**
     * Default constructor for Task instance
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor with additional information of completion status
     *
     * @param description task description
     * @param isDone      whether the task is marked as done
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Empty constructor for Task instance
     */
    public Task() {
    }

    /**
     * Retrieve the status icon of Task
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? CHECK_MARK : UNCHECK_MARK);
    }

    /**
     * Set the completion status of current task
     *
     * @param isDone whether the task is mark as done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * get the description of current task
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get the completion status of current task
     *
     * @return completion status of current task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * get the task type of current task
     *
     * @return type of task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * get the time associated with the task, if there is any
     *
     * @return the time associated with current task
     */
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
