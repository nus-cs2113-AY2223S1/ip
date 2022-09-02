public class Event extends Task{
    private String timeOfEvent;

    public Event(String name, String timeOfEvent){
        super(name);
        this.timeOfEvent = timeOfEvent;
    }

    public String toString() {
        return  "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }
}