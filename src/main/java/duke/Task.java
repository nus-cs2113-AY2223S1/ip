package duke;

/**
 * Represents a task in the Task List. A Task object corresponds to a string which represents the task name,
 * a boolean to represent whether the task has been completed and a string to represent the type of task.
 */
public class Task {
    private String name;
    private boolean isCompleted;
    private String taskType;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        this.taskType = "[NA]";
    }

    public String getName(){
        return name;
    }

    public boolean getIsCompleted(){
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    //returns the checkbox as a string
    public String getCheckBox() {
        if (isCompleted) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * Returns type of task, checkbox and name of task in String format.
     *
     * @return Task in String format.
     */
    public String toString() {
        return taskType + getCheckBox() + " "+ getName();
    }
}
