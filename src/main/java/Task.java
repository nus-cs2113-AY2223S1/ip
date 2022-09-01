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

    public Boolean hasCompleted() {
        return this.isCompleted;
    }

    public String getTaskName() {
        return this.item;
    }

    public String getCompleteDescription() {
        String output = "[ ][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
