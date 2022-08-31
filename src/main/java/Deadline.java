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
        super.deadline = "( by :";
        while(i < splitInputs.length) {
            super.deadline += splitInputs[i++];
            super.deadline += " ";
        }
        super.deadline += ")";
    }
}