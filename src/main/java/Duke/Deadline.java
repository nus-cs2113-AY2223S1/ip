package Duke;
public class Deadline extends Task{
    public Deadline(String[] splitInputs) {
        super();
        super.taskType = "D";
        int i = 2;
        super.description = splitInputs[1];
        while(i < splitInputs.length && !(splitInputs[i].contains("/by"))) {
            this.description += " ";
            super.description += splitInputs[i++];
        }
        i += 1;
        super.dueDate = "( by :";
        while(i < splitInputs.length) {
            super.dueDate += splitInputs[i++];
            super.dueDate += " ";
        }
        super.dueDate += ")";
    }
}