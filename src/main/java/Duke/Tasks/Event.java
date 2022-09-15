package Duke.Tasks;

public class Event extends Task {
    protected String startTime;

    public Event(String description, char taskType, String startTime) {
        super(description, taskType);
        this.startTime = startTime;
    }

    public String taskStatusWithDescriptionText() {
        String startTimeText = "(at: " + startTime + ")";
        return super.taskStatusWithDescriptionText() + startTimeText;
    }

    @Override
    public String taskDataFileText() {
        return super.taskDataFileText() + "| " + startTime;
    }
}