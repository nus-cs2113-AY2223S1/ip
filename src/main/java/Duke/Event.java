package Duke;

public class Event extends Task {
    /**
     * Returns time of event
     * @return time of event
     */
    @Override
    public String getTime() {
        return at;
    }

    /**
     * Sets time of event
     * @param at time of event to be set
     */
    @Override
    public void setTime(String at) {
        this.at = at;
    }

    public String at;
    public Event(String description, String at, boolean marked) {
        this.description = description;
        this.at = at;
        this.isMark = marked;
    }

    /**
     * Returns "[E]" to signal event type task
     * @return "[E]" to signal type of task
     */
    @Override
    public String getType() {
        return "[E]";
    }
}
