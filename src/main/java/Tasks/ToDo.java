package Tasks;

public class ToDo extends Task {
    public ToDo(String taskTitle) {
        super(taskTitle);
        super.setTaskType("ToDo");
    }

    public ToDo(String taskTitle, boolean taskDone) {
        super(taskTitle, taskDone);
        super.setTaskType("ToDo");
    }
}