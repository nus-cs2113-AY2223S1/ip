public class Event extends Task {


    public Event(String taskName, String eventTime) {
        super(taskName);
        super.setTaskType("Event");
        super.setEventTime(eventTime);
    }

    public Event(String taskName, boolean status) {
        super(taskName, status);
        super.setTaskType("Event");
    }
}
