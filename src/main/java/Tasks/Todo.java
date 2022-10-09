package Tasks;

/**
 * Represents a Todo Task, extended from Task class.
 * Contains item description and completion status.
 */
public class Todo extends Task {
    public Todo (String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Returns the formatted description for the Todo.
     * Format: [T][{Done}] {description}
     * - Done: X or {emptySpace}
     *
     * @return Formatted String.
     */
    @Override
    public String getCompleteDescription() {
        String output = "[T][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
