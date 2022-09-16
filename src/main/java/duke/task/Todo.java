package duke.task;
public class Todo extends Task{
    public String getTaskIcon() {
        return "T";
    }

    public Todo(String description) {
        super(description);
    }
    public String getDescription() {
        int firstDivider = description.indexOf("todo");
        String descTask = description.substring(firstDivider);
//        int secondDivider = description.indexOf("/at");
//        String descTask = description.substring(firstDivider + 5,secondDivider - 1);
//        String duration = description.substring(secondDivider + 4);
        return descTask;
    }
}
