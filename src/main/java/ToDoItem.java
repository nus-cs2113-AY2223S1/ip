public class ToDoItem {
    private final String TEXT;
    private boolean isDone;

    public ToDoItem(String text) {
        TEXT = text.trim();
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + TEXT;
    }

    public String getText() {
        return TEXT;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
