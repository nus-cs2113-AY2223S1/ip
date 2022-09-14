package duke;

public class Deadline extends Task {

    private String by;

    public String getBy(){

        return this.by;

    }

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        this.taskType = 'D';
    }

    public void setBy(String by){
        this.by = by;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" ("+this.by+")");
    }

    @Override
    public String getSaveString(){
        return taskType + "|" + isDone +  "|" + description + "|" + by;
    }

}