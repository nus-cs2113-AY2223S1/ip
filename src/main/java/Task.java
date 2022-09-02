public class Task {
    private String item;
    private boolean isCompleted;

    public Task(String item) {
        this.item = item;
        isCompleted = false;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean hasCompleted() {
        return isCompleted;
    }

    public String getTaskName() {
        return item;
    }

    public String getCompleteDescription() {
        String output = "[ ][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
