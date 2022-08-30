public class Deadline extends Task {
    protected String[] descriptions;
    protected String dueTime;

    public Deadline(String commandArgs) {
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
        this.description = descriptions[0];
        this.dueTime = descriptions[1];
    }

    protected String getDueTime() {
        return this.dueTime;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    protected String assembleResponse() {
        String response = "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + getDescription() + "(by:"
                + getDueTime() + ")";
        return response;
    }

    protected String[] parseDescriptions(String commandArgs) {
        String[] descriptions = commandArgs.split("/by");
        return descriptions;
    }
}
