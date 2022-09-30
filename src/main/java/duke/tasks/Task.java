package duke.tasks;

/**
 * Represents all the tasks that user can add
 */
public class Task {
    public String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Gets the description of the task
     *
     * @returns the task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }

    public String toString() {
        String prefix = "[] ";
        if (isDone){
            prefix = "[X] ";
        }
        return prefix + description;
    }
}
