package Duke.data.tasks;
public abstract class Task {
    protected String description;
    protected String dueDate;
    protected String taskType;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (this.isDone) ? "\u2713" : "X";
    }

    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    public String getMarkStatus() {
        return "[" + getStatusIcon() + "]";
    }

    public void setDone(boolean isMark) {
        isDone = isMark;
    }

    public void printTask() {
        System.out.println("\t " + this.getTaskType() +
                this.getMarkStatus() + " " +
                this.description + "\t\t" + this.dueDate);
    }

    @Override
    public String toString() {
        return this.getMarkStatus() + " " + this.taskType + " " +
                this.description + " " + this.dueDate;
    }

}
