package Duke;

public class Deadline extends Task {
    String by;
    public Deadline(String description, String by, boolean marked) {
        this.description = description;
        this.by = by;
        this.isMark = marked;
    }
    @Override
    public String getTime() {
        return by;
    }
    @Override
    public void setTime(String by) {
        this.by = by;
    }
    @Override
    public String getType() {
        return "[D]";
    }
}
