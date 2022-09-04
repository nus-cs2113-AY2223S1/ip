public class Deadline extends Todo {
    protected String by;

    public Deadline(String command, Character lastChar) {
        super(command, lastChar);
        int byStartIdx = command.indexOf('/') + "by _".length();
        setBy(command.substring(byStartIdx));
    }

    public String getBy() {
        return this.by;
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
        return super.toString() + " (by: " + getBy() + ")";
    }
}
