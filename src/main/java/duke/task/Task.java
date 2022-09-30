package duke.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        this.isDone = !done;
    }

    // @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the boolean value of the task being done.
     * This value will be used to write to the data file.
     * @param task The task to be checked.
     * @return Returns the value for the boolean value of the task.
     */
    public static String getBoolValue(Task task) {
        String boolValue;
        if (task.isDone()) {
            boolValue = "1";
        } else {
            boolValue = "0";
        }
        return boolValue;
    }
}