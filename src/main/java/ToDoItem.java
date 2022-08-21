public class ToDoItem {
    private String text;
    private boolean isDone;

    public ToDoItem(String text) {
        this.text = text.trim();
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + text;
    }

    public String getText(){
        return text;
    }
    public String getStatusIcon(){
        return isDone ? "⬤" : " ";
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
