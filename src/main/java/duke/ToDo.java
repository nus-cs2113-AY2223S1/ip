package duke;

/**
 * Class representing a ToDo
 */
public class ToDo extends Task{

    /**
     * Constructor for ToDo to initialise it with description passed in.
     * Sets the task type.
     *
     * @param description Description of ToDo.
     */
    public ToDo(String description){
        super(description);
        this.taskType = 'T';
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println();
    }
}
