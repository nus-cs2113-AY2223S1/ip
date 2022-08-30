public class Task {

    private String description;
    private boolean isMarked;


    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[√]" + description;
        }
        else return "[ ]" + description;
    }
}
