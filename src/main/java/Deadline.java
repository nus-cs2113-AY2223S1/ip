public class Deadline extends Task{
    protected String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    /*public Deadline(String command) {
        int DescStartIdx = command.indexOf(' ') + 1;
        int DescEndIdx = command.indexOf('/');
        String description = command.substring(DescStartIdx,DescEndIdx);
        this.description = description;
        this.isDone = false;
    }*/

    public Deadline(String command, Character lastChar) {
        super(command, lastChar);
        int byStartIdx = command.indexOf('/') + 4;
        this.by = command.substring(byStartIdx);
    }
}
