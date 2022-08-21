public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatus() {
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    public void setCompletion(boolean isComplete) {
        this.isCompleted = isComplete;
    }

    public String getDescription() {
        return description;
    }
}
