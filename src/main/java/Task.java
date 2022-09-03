public abstract class Task {

    private int taskNumber;
    private String name;
    private boolean isDone;

    public static int tasksCount = 0;

    public Task(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new DukeException(ExceptionType.MISSING_DESCRIPTION);
        }
        setTaskNumber(TaskManager.getTasksCount());
        if (arguments.contains("/")) {
            arguments = arguments.substring(0, arguments.indexOf('/'));
        }
        setName(arguments.trim());
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

    public static String extractTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new DukeException(ExceptionType.MISSING_TIME);
        }
        return arguments.substring(arguments.indexOf('/') + 1);
    }


}
