package duke.tasks;

/**
 * This class is used for tasks that need to be done at a specific date/time.
 * Example of a event task: attend career talk at 23/12/2001 2359
 */
public class Event extends Task {
    protected String startTime;

    public Event(String description, char taskType, String startTime) {
        super(description, taskType);
        this.startTime = startTime;
    }

    /**
     * Returns a string of the task's type, status on mark done, description and event date/time
     * @return Formatted string for displaying in the Ui
     */
    public String taskStatusWithDescriptionText() {
        String startTimeText = "(at: " + startTime + ")";
        return super.taskStatusWithDescriptionText() + startTimeText;
    }

    /**
     * Returns a string of the task's type, status on mark done and description,
     * where 1 represents the task is marked done, and 0 means not done
     * @return Formatted string for writing in the data file
     */
    @Override
    public String taskDataFileText() {
        return super.taskDataFileText() + "| " + startTime;
    }
}