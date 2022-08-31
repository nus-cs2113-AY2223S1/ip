public class Event extends Task{
    public Event(String[] splitInputs) {
        super();
        super.taskType = "E";
        int i = 2;
        super.description = splitInputs[1];
        while(i < splitInputs.length && !(splitInputs[i].contains("/at"))) {
            this.description += " ";
            super.description += splitInputs[i++];
        }
        i += 1;
        super.deadline = "( at :";
        while(i < splitInputs.length) {
            super.deadline += splitInputs[i++];
            super.deadline += " ";
        }
        super.deadline += ")";
    }
}
