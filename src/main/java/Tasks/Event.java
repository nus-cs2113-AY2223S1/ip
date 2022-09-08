package Tasks;

public class Event extends Task {
    public Event(String taskTitle, String taskDate) {
        super(taskTitle);
        super.setTaskType("Event");
        super.setTaskDate(taskDate);
    }
}