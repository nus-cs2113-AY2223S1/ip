package duke.task;

public class Task {

    private String keyword;
    private String description;
    private boolean isDone;

    public Task(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }

}
