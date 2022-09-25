package task;

/**
 * Child of parent class Task
 * Contains only description which is the name of todo
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    /**
     * @return full details of todo including:
     * type as "[T]"
     * status of mark/unmark, [X] if marked, [ ] if unmarked
     * description which is name of todo
     */
    @Override
    public String toString() {
        return ("[T][" + super.getStatusIcon() + "] " + super.description);
    }
    /**
     * @return type of child, which is "T"
     */
    @Override
    public String getTaskClass() {
        return "T";
    }
}
