package duke.tasks;

/**
 * Represents a Todo Task to be added to taskList
 */
public class Todo extends Task {

    /** Constructors with and without isDone */
    public Todo(String taskDescription) {
        super(taskDescription);
    }
    public Todo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /** Formatted <code>showTask</code> method specific for Todo*/
    @Override
    public String showTask() {
        return "[T][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription;
    }

    /** Formatted <code>writeTaskToFile</code> method specific for Todo*/
    @Override
    public String writeTaskToFile(){
        return "T | " + (isDone ? 1 : 0) + " | " + taskDescription;
    }

}