package duke.task;
public class Deadline extends Task{
    public String getTaskIcon() {
        return "D";
    }

    public Deadline(String description) {
        super(description);
    }
    public String getDescription() {
        int firstDivider = description.indexOf("deadline");
        int secondDivider = description.indexOf("/by");
        String descTask = description.substring(firstDivider + "deadline".length() + 1,secondDivider - 1);
        String duration = description.substring(secondDivider + "/by".length() + 1);
        String descString = descTask + " (by: " + duration + ")";
        return descString;
    }
}
