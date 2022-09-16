package duke;

public class Todo extends Task {

    public Todo(String task,boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("T|" + isDoneValue + "|" + this.task +"\n");
        return taskDetails;
    }
}
