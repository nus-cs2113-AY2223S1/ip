/**
 * Defines The Event task, which has a description and a event time 
 */
public class Event extends Task {
    protected String[] descriptions;
    protected String eventTime;

    /**
    * Initializes an event task with a user input
    * @param commandArgs user Input
    * @throws DukeException If user input is invalid 
    */
    public Event(String commandArgs) throws DukeException {
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
        this.description = descriptions[0];
        this.eventTime = descriptions[1];
    }

    /**
    * Initializes a deadline task with direct information
    * (usually used when restoring task from disk)
    * @param description the description of the task
    * @param isDone the boolean var to state whether the task is done
    * @param eventTime the event time of the task
    */
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

    /**
    * Assembles the information of the task to store it on disk
    * @return the assembled information
    */
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
