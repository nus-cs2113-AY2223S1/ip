public class Event extends Task {
    protected String startTime;

    public Event(String description, char taskType, String startTime) {
        super(description, taskType);
        this.startTime = startTime;
    }

    public String printTask() {
        String startTimeText = "(at: " + startTime + ")";
        return super.printTask() + startTimeText;
    }
}