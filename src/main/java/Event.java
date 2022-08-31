public class Event extends Task {
    protected String dateTime;

    public Event(String description, String dateTime){
        this.description = description;
        this.isDone = false;
        this.taskType = "E";
        this.dateTime = dateTime;
    }

    public String getDateTime(){
        return this.dateTime;
    }
}
