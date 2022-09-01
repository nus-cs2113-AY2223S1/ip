public class ToDo extends Task {
    /** To check if the task is to do task */
    protected boolean isToDo;

    public ToDo(String description) {
        super(description);
        this.isToDo = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark task with toDo as "T"
        return (isToDo ? "T" : " ");
    }

    @Override
    public void markTypeTask() {
        isToDo = true;
    }
}
