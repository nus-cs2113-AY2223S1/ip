package duke;

/**
 * Subclass of the Task class containing methods to classify a task as a Todo task.
 */
public class Todo extends Task {

    public Todo(String task,boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns a formatted String to be printed and displayed to the user.
     *
     * @return String formatted to specify that the task is a Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns a formatted String to be stored into the data file.
     *
     * @return String formatted to specify that the task is a Todo task.
     */
    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("T|" + isDoneValue + "|" + this.task +"\n");
        return taskDetails;
    }
}
