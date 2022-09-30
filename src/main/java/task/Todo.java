package task;

/**
 * in charge of type todo of the task
 */
public class Todo extends Task{

    /**
     * used when only description is given
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
        System.out.println("  " + this);
    }

    /**
     * used when both the description and isDone status are given
     *
     * @param description description of the task
     * @param status the status of the task
     */
    public Todo(String description, boolean status) {
        super(description, status);
        System.out.println("  " + this);
    }


    public String toString() {
        return "[T]" + super.toString();
    }
}
