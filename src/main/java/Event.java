public class Event extends Task{
    public Event(String description) {
        super(description);
    }

    public String getCommandIcon(){return "[E]";}

    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + getDescription().replace('/', '(').replace("at" , "at:").replaceAll("\\s+$", "") + ")";
    }
}
