package Duke.data.tasks;
public class Todo extends Task{
    public Todo(String[] splitInputs) {
        super();
        super.taskType = "T";
        int i = 2;
        super.description = splitInputs[1];
        while(i < splitInputs.length) {
            this.description += " ";
            super.description += splitInputs[i++];
        }
        super.dueDate = "";
    }
}
