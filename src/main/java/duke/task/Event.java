package duke.task;
public class Event extends Task{
    public String getTaskIcon() {
        return "E";
    }

    public Event(String description) {
        super(description);
    }
    public String getDescription() {
        int firstDivider = description.indexOf("event");
        int secondDivider = description.indexOf("/at");
        String descTask = description.substring(firstDivider + "event".length() + 1,secondDivider - 1);
        String duration = description.substring(secondDivider + "/at".length() + 1);
        String descString = descTask + " (at: " + duration + ")";
        return descString;
    }
}
