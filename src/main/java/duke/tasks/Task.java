package duke.tasks;

public class Task {
    public String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        isDone = true;
    }

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
