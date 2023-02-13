/**
 * Todo class
 * extends Task class to store a particular type of task (todo)
 * contains a description of the task (String description)
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
