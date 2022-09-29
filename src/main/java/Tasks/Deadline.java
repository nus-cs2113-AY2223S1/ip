package Tasks;

public class Deadline extends Task {
    private String time;

    public Deadline(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getCompleteDescription() {
        String output = "[D][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + " (by: " + getTime() + ")\n";
        return output;
    }
}
