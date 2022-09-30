package duke;

/**
 * Class representing a Deadline
 */
public class Deadline extends Task {

    /** Deadline inputted by user */
    private String deadlineDate;

    public String getDeadlineDate(){

        return this.deadlineDate;

    }

    /**
     * Constructor for Deadline to initialise it with description and deadlineDate passed in.
     * Sets the task type.
     *
     * @param description Description of what deadline is for.
     * @param deadlineDate Date of deadline.
     */
    public Deadline(String description, String deadlineDate){
        super(description);
        this.deadlineDate = deadlineDate;
        this.taskType = 'D';
    }

    public void setDeadlineDate(String deadlineDate){
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" ("+this.deadlineDate +")");
    }

    @Override
    public String getSaveString(){
        return taskType + "|" + isDone +  "|" + description + "|" + deadlineDate;
    }

}