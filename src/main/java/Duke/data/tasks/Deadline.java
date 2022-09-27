package Duke.data.tasks;

public class Deadline extends Task{
    public Deadline(String userInput) {
        super();
        super.taskType = "D";
        String[] splitInputs = userInput.split("/by", 2);
        super.description = splitInputs[0];
        super.dueDate = "by :" + splitInputs[1];
    }
}