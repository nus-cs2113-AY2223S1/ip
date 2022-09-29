package Tasks;

import java.util.ArrayList;

public class Task {
    private String item;
    private boolean isCompleted;

    public Task(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
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

    public String getTime() {
        return "";
    }

    public String getCompleteDescription() {
        String output = "[ ][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
