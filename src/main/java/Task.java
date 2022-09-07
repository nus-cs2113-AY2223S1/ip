public class Task {
    private String item;
    private boolean isCompleted;

    protected Task(String item) {
        this.item = item;
        isCompleted = false;
    }

    protected void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    protected boolean hasCompleted() {
        return isCompleted;
    }

    protected String getTaskName() {
        return item;
    }

    protected String getCompleteDescription() {
        String output = "[ ][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
