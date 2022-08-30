public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
        super.setTaskType("ToDo");
    }

    public ToDo(String taskName, boolean status) {
        super(taskName, status);
        super.setTaskType("ToDo");
    }
}
