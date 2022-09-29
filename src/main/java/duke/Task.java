package duke;

/**
 * Task is the abstract class base for all task subclasses which store the total
 * number of tasks, the description of the task and if the task is done. The class
 * can also generate the formatted overall information about a task object.
 */
public abstract class Task {
    private boolean isDone;
    String description;
    public static int numOfTasks = 0;

    public Task (String description){
        this.description = description;
        isDone = false;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public String getDescription(){
        return description;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public static int getNumOfTasks(){
        return numOfTasks;
    }

    public String toString(){
        String isDoneNotation;
        if (isDone == true){
            isDoneNotation = "[X]";
        }
        else {isDoneNotation = "[ ]";}

        return isDoneNotation + " " + description;
    }
}
