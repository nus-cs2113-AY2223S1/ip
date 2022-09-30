package duke.tasks;

/**
 * Represents the task of type Todo
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    @Override
    public String toString(){
        return "[T]" + super.toString();
    }


}