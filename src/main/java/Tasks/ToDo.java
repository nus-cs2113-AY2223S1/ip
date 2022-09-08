package Tasks;

public class ToDo extends Task {
    public ToDo(String taskTitle) {
        super(taskTitle);
        super.setTaskType("ToDo");
    }
}