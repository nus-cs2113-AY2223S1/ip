package duke.task;

/**
 * Represents a general task under no specific category that the user wishes to take note of in their list.
 */
public class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructs an instance of the Task class to contain a task description and to track whether the task has been done by the user or not.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Checks a task's done status and returns a corresponding string to reflect the status.
     *
     * @return "X" if task has been done or a whitespace character if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    /**
     * Overrides the Object class's toString method to return a more informative string that better reflects the user's task.
     *
     * @return A formatted string of the task's description and done status.
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public String formattedInformation() { //dummy function for overriding by subclasses
        return "";
    }

    public String getDescription() {
        return this.description;
    }
}