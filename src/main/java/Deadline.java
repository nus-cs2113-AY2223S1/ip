public class Deadline extends Task {

    public Deadline(String taskName, String deadline) {
        super(taskName);
        super.setTaskType("Deadline");
        super.setTaskDeadline(deadline);
    }

    public Deadline(String taskName, boolean status) {
        super(taskName, status);
        super.setTaskType("Deadline");
    }
}

