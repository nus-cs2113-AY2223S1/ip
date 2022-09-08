/**
 * Tasks to be added with attribute to track completion status
 * */

public class Task {
    private String taskDescription;
    private Boolean isDone;
    // constructor without isDone
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    // getters
    public String getTaskDescription() {
        return this.taskDescription;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    // setters
    public void setIsDone(Boolean doneStatus) {
        this.isDone = doneStatus;
    }

}
