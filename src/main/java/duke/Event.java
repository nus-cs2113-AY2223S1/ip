package duke;

/**
 * Class representing an event.
 */
public class Event extends Task {

    /** Timing when the event is occurring */
    private String timing;

    public String getTiming(){
        return this.timing;
    }

    /**
     * Constructor for Event to initialise it with description and timing passed in.
     *
     * @param description Description of the event.
     * @param timing Timing when theevent occurs.
     */
    public Event(String description, String timing){
        super(description);
        this.timing = timing;
        this.taskType = 'E';
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" ("+this.timing+")");
    }

    @Override
    public String getSaveString(){
        return taskType + "|" + isDone +  "|" + description + "|" + timing;
    }
}
