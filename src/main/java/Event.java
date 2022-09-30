
/**
 * Class that creates the Event object that is an extended class of Task
 */
public class Event extends Task{
    protected String date;

    /**
     * Constructor for the Event object that provides values for the name, date, and
     * doneness of the event being put on the list
     * @param name name of event
     * @param done doneness of event
     * @param date date of event
     */
    public Event(String name, boolean done, String date){
        super(name, done);
        this.date = date;
    }

    /**
     * Getter class for the date of the event
     * @return date
     */
    @Override
    public String getDate(){
        return date;
    }

    /**
     * Method that creates a new string format for the event object to be presented as
     * @return the String format for an event
     */
    @Override
    public String toString() {
        return "[E]"+super.toString()+" (at: "+date+")";
    }

    /**
     * Gets the name of the type of object as a string
     * @return "Event"
     */
    @Override
    public String classInfo() {
        return "Event";
    }
}
