package Tasks;

public class Event extends Task {
    public Event(String taskTitle, String taskDate) {
        super(taskTitle);
        super.setTaskType("Event");
        super.setTaskDate(taskDate);
    }

    public Event(String taskTitle, boolean taskDone, String taskDate) {
        super(taskTitle, taskDone);
        super.setTaskType("Event");
        super.setTaskDate(taskDate);
    }
}