public class Task {
    protected String description;
    protected boolean isDone;
    public int remainingTaskCount = 0;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        isDone = true;
        remainingTaskCount--;
    }

    public void markAsUndone() {
        isDone = false;
        remainingTaskCount++;
    }

    public String toString() {
        String prefix = "[] ";
        if (isDone){
            prefix = "[X] ";
        }
        return prefix + description;
    }
}
