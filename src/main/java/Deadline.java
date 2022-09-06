public class Deadline extends Task {
    protected String[] descriptions;
    protected String dueTime;

    public Deadline(String commandArgs) throws DukeException {
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

    protected String[] parseDescriptions(String commandArgs) throws DukeException {
        String[] descriptions = commandArgs.split("/by");
        if (descriptions.length != 2) {
            throw new DukeException("DeadlineDescriptionError");
        }
        return descriptions;
    }
}
