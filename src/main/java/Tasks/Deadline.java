package Tasks;

public class Deadline extends Task {
    public Deadline(String taskTitle, String deadline) {
        super(taskTitle);
        super.setTaskType("Deadline");
        super.setTaskDeadline(deadline);
    }

    public Deadline(String taskTitle, boolean taskDone, String deadline) {
        super(taskTitle, taskDone);
        super.setTaskType("Deadline");
        super.setTaskDeadline(deadline);
    }
}
