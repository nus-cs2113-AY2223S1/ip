public class Deadline extends Task {

    private String deadlineTime;

    public Deadline(String name, String deadlineTime) {
        super(name);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    @Override
    public String taskDescription() {
        return String.format("%s (%s)", this.getName(), this.deadlineTime);
    }
}
