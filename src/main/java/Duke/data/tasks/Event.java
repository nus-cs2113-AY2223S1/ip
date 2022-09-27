package Duke.data.tasks;

public class Event extends Task{
    public Event(String userInput) {
        super();
        super.taskType = "E";
        String[] splitInputs = userInput.split("/at", 2);
        super.description = splitInputs[0];
        super.dueDate = "at :" + splitInputs[1];
    }
}
