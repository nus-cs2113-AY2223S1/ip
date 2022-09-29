package Duke.Tasks;

/**
 * This class is used to create tasks.
 * Each Task must contain a description, whether marked done or not, and the tasktype.
 */
public class Task {
    public String description;
    public boolean isDone;
    public char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public char getTaskType() {
        return taskType;
    }

    /**
     * Returns a string of the task's type, status on mark done and description
     * @return Formatted string for displaying in the Ui
     */
    public String taskStatusWithDescriptionText() {
        String output = "[" + this.getTaskType() + "]";
        output += "[" + this.getStatusIcon() + "] ";
        output += this.description;
        return output;
    }

    /**
     * Returns a string of the task's type, status on mark done and description,
     * where 1 represents the task is marked done, and 0 means not done
     * @return Formatted string for writing in the data file
     */
    public String taskDataFileText() {
        String isDoneString = (this.isDone) ? "1" : "0";
        return taskType + " | " + isDoneString + " | " + description;
    }
}
