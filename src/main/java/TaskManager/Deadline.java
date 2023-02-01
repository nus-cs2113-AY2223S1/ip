package TaskManager;
public class Deadline extends Task {
    private String dueDate;
    
    public Deadline(String description, String dueDate){
        this.description = description;
        this.isDone = false;
        this.taskType = "D";
        this.dueDate = dueDate;
    }

    public String getDueDate(){
        return this.dueDate;
    }
}
