public class Deadline extends Task implements FormatChecker {
    private static final String DEADLINE_MARK = "[D]";
    private static final TaskType taskType = TaskType.DEADLINE;
    private final String time;

    /**
     * Default constructor for Deadline instance
     *
     * @param description description of Deadline
     * @param time        time of deadline
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Construct a deadline object given additional status of completion
     *
     * @param description description of deadline
     * @param isDone      whether the deadline is marked as done
     * @param time        time of the deadline
     */
    public Deadline(String description, Boolean isDone, String time) {
        this.description = description;
        this.isDone = isDone;
        this.time = time;
    }

    /**
     * get the icon that indicates whether the deadline is marked
     *
     * @return string of icon for mark status
     */
    @Override
    public String getStatusIcon() {
        return DEADLINE_MARK + super.getStatusIcon();
    }

    /**
     * get type of task, which is deadline
     *
     * @return type of task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * get the time of deadline
     *
     * @return time of deadline
     */
    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s %s(by: %s)", this.getStatusIcon(), description, this.time);
    }
}
