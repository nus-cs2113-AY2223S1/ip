public class Event extends Task implements FormatChecker {
    private static final String EVENT_MARK = "[E]";
    private static final TaskType taskType = TaskType.EVENT;
    private final String time;

    /**
     * Default constructor for Event instance
     *
     * @param description description of Event
     * @param time        time of event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Construct a deadline object given additional status of completion
     *
     * @param description description of event
     * @param isDone      whether the event is marked as done
     * @param time        time of the event
     */
    public Event(String description, Boolean isDone, String time) {
        this.description = description;
        this.isDone = isDone;
        this.time = time;
    }

    /**
     * get the icon that indicates whether the event is marked
     *
     * @return string of icon for mark status
     */
    @Override
    public String getStatusIcon() {
        return EVENT_MARK + super.getStatusIcon();
    }

    /**
     * get type of task, which is event
     *
     * @return type of task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * get the time of event
     *
     * @return time of event
     */
    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s %s(at: %s)", this.getStatusIcon(), description, this.time);
    }
}
