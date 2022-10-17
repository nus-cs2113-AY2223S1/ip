package Duke;

public class Task {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;
    boolean isMark;

    public boolean isMark() {
        return isMark;
    }

    public void mark(boolean isMark) {
        this.isMark = isMark;
    }
    public String getType() {
        return null;
    }

    public void setTime(String time) {}
    public String getTime() {
        return null;
    }
}
