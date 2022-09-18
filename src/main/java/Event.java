public class Event extends Task {
    private String timeOfEvent;
    private String taskType;

    public Event(String name, String timeOfEvent) {
        super(name);
        this.timeOfEvent = timeOfEvent;
        this.taskType = "[E]";
    }

    public String toString() {
        return  taskType + getCheckBox() + " "+ getName() + " (at: " + timeOfEvent + ")";
    }
    public String getFileInput() {
        return "E" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName() + " | " + timeOfEvent;
    }
}