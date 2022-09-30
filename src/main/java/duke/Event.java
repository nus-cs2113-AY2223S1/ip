package duke;

import java.time.LocalDate;

public class Event extends Task {
    private String timing;

    public String getTiming(){
        return this.timing;
    }

    public Event(String description, String timing){
        super(description);
        this.timing = timing;
        this.taskType = 'E';
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" ("+this.timing+")");
    }

    @Override
    public String getSaveString(){
        return taskType + "|" + isDone +  "|" + description + "|" + timing;
    }
}
