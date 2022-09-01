public class ToDo extends Task {
    /** To check if the task is to do task */
    protected boolean isToDoCheck;

    public ToDo(String description) {
        super(description);
        this.isToDoCheck = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark task with toDo as "T"
        return (isToDoCheck ? "T" : " ");
    }

    @Override
    public void markTypeTask() {
        isToDoCheck = true;
    }
}
