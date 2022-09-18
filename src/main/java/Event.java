public class Event extends Task {
    protected String[] descriptions;
    protected String eventTime;

    public Event(String commandArgs) throws DukeException {
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
        this.description = descriptions[0];
        this.eventTime = descriptions[1];
    }

    public Event(String description, boolean isDone, String eventTime) {
        super(description,isDone);
        this.eventTime = eventTime;
    }

    protected String getEventTime() {
        return this.eventTime;
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    protected String assembleResponse() {
        String response = "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + getDescription() + "(at:"
                + getEventTime() + ")";
        return response;
    }

    @Override
    public String getStorageFormat(){
        String status = isDone ? "1" : "0";
        return getTaskType()+" | "+status+" | "+description+" | "+eventTime;
    }

    protected String[] parseDescriptions(String commandArgs) throws DukeException {
        String[] descriptions = commandArgs.split("/at");
        if (descriptions.length != 2) {
            throw new DukeException("EventDescriptionError");
        }
        return descriptions;
    }
}
