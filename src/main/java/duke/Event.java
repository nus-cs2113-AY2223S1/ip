package duke;
/**
 * Event is a subclass of Task. The Event object inherits the attribute name from the superclass.
 * Event is denoted by task type "[E]" and requires the attribute time of event in String format for Event to be valid.
 */
public class Event extends Task {
    private String timeOfEvent;
    private String taskType;

    public Event(String name, String timeOfEvent) {
        super(name);
        this.timeOfEvent = timeOfEvent;
        this.taskType = "[E]";
    }

    /**
     * Returns type of task, checkbox, name of task and time of event in String format.
     *
     * @return Event in String format.
     */
    public String toString() {
        return  taskType + getCheckBox() + " "+ getName() + " (at: " + timeOfEvent + ")";
    }

    /**
     * Returns Event attributes in String format separated by delimiter "|"
     *
     * @return Event in String format for storage in local file.
     */
    public String getFileInput() {
        return "E" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName() + " | " + timeOfEvent;
    }
}