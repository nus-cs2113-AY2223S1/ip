package Duke.data.tasks;
import Duke.data.exception.DukeException;

public class Deadline extends Task{
    public Deadline(String[] splitInputs) throws DukeException {
        super();
        super.taskType = "D";
        int i = 2;
        super.description = splitInputs[1];
        while(i < splitInputs.length && !(splitInputs[i].contains("/by"))) {
            this.description += " ";
            super.description += splitInputs[i++];
        }
        i += 1;
        if(i >= splitInputs.length) {
            throw new DukeException();
        }
        super.dueDate = "( by :";
        while(i < splitInputs.length) {
            super.dueDate += splitInputs[i++];
            super.dueDate += " ";
        }
        super.dueDate += ")";
    }
}