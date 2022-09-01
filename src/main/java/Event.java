public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String getCompleteDescription() {
        String output = "[E][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + " (at: " + getTime() + ")\n";
        return output;
    }
}
