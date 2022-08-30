public class Event extends Task{
    protected String[] descriptions;
    protected String eventTime;

    public Event(String commandArgs) {
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
        this.description = descriptions[0];
        this.eventTime = descriptions[1];
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

    protected String[] parseDescriptions(String commandArgs) {
        String[] descriptions = commandArgs.split("/at");
        return descriptions;
    }
}
