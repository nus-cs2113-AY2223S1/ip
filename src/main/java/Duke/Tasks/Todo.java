package Duke.Tasks;

public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String getCompleteDescription() {
        String output = "[T][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
