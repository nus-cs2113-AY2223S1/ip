public class Task {

    private int taskNumber;
    private String name;
    private boolean isDone;

    public Task(String name) {
        setTaskNumber(TaskManager.getTasksCount());
        setName(name);
        this.isDone = false;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markAsDone() {
        this.isDone = true;
        listTask();
    }

    public void markAsNotDone() {
        this.isDone = false;
        listTask();
    }

    public char taskType() {
        return '-';
    }

    private char doneIcon() {
        if (this.isDone) {
            return 'X';
        }
        return ' ';
    }

    public String taskDescription() {
        return this.name;
    }

    public String listTask() {
        return String.format("%d.[%c][%c] %s", this.taskNumber, taskType(), doneIcon(), taskDescription());
    }

}
