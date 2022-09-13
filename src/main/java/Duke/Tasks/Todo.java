package Duke.Tasks;

public class Todo extends Task {
    public Todo (String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String getCompleteDescription() {
        String output = "[T][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
