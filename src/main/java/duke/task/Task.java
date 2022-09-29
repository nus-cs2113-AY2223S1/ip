package duke.task;

import duke.Storage;

public class Task {
    private String description;
    private boolean isDone;
    public final String isDoneString = "X";
    public final String isNotDoneString = " ";

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        setMark();
        System.out.println(" [X] " + description);
    }

     public void setMark(){
         isDone = true;
     }

    public void unmarkDone() {
        System.out.println(" OK, I've marked this task as not done yet:");
        isDone = false;
        System.out.println(" [ ] " + description);
    }

    public String getIsDoneMarking() {
        if (isDone) {
            return isDoneString;
        }
        return isNotDoneString;
    }
    public String isDoneFileFormat(){
        String isDone = "0";
        if (getIsDone()){
            isDone = "1";
        }
        return isDone;
    }
    public boolean getIsDone(){
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileFormat() {
        return Storage.divider + isDoneFileFormat()
                + Storage.divider + getDescription();
    }

}
