package duke.task;

public class Task {

    protected String description;
    protected boolean isMarked;
    protected final int prefex_length = 3;


    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getTaskDescription() {
        return description;
    }

    public boolean getTaskMark() {
        return isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[T][√]" + description;
        }
        else return "[T][ ]" + description;
    }
}
