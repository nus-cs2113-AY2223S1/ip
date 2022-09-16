package duke.tasks;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }
    public Todo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    @Override
    public String showTask() {
        return "[T][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription;
    }

    @Override
    public String writeTaskToFile(){
        return "T | " + (isDone ? 1 : 0) + " | " + taskDescription;
    }

}