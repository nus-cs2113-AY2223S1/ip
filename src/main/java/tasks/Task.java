package tasks;

/**
 * Represents a generic task for this application. 
 * Deadline, Event and Todo objects can inherit this class
 */
public class Task {
    public String descriptionString;
    public boolean isDone;

    public Task(String descriptionString) {
        this.descriptionString = descriptionString;
        this.isDone = false;
    }

    public Task() {
        this.isDone = false;
    }

    /**
     * Obtains the status icon for the task
     * 
     * @return status icon for marked or unmark
     */
    public String getStatusIcon() {
        return (isDone ? " [X]" : " [ ]"); // mark done task with X
    }

    /**
     * Obtains the description string of the task
     * 
     * @return string that describes the task
     */
    public String getDescription() {
        return descriptionString;
    }

    /**
     * Sets the status of isDone to be true
     * 
     * @return message to state that marked done has been completed
     */
    public String markDone() {
        this.isDone = true;
        return "marked done!";
    }

    /**
     * Sets the status of isDone to be false
     * 
     * @return message to state that unmark has been completed
     */
    public String unmark() {
        this.isDone = false;
        return "unmarked.";
    }

    @Override
    public String toString() {
        return this.descriptionString + this.getStatusIcon();
    }

    /**
     * Creates the string that represents the task when task
     * needs to be saved to a file. This method is to be overwritten
     * by classes that inherit the Task class.
     * 
     * @return empty string for generic task.
     */
    public String createFileString() {
        return "";
    }
}
