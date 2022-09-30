package duke.tasks;

/**
 * This class is used for tasks that need to be done before a specific date/time.
 * Example of a deadline task: submit iP by 23/12/2001 2359
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, char taskType, String deadline) {
        super(description, taskType);
        this.deadline = deadline;
    }

    /**
     * Returns a string of the task's type, status on mark done, description and deadline
     * @return Formatted string for displaying in the Ui
     */
    public String taskStatusWithDescriptionText() {
        String deadlineText = "(by: " + deadline + ")";
        return super.taskStatusWithDescriptionText() + deadlineText;
    }

    /**
     * Returns a string of the task's type, status on mark done and description,
     * where 1 represents the task is marked done, and 0 means not done
     * @return Formatted string for writing in the data file
     */
    @Override
    public String taskDataFileText() {
        return super.taskDataFileText() + "| " + deadline;
    }
}