package Tasks;

public class Event extends Task {
    private String time;

    public Event(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getCompleteDescription() {
        String output = "[E][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + " (at: " + getTime() + ")\n";
        return output;
    }
}
