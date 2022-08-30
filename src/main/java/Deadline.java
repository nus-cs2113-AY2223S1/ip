public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }
    public String getCommandIcon(){return "[D]";}

    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + getDescription().replace('/', '(').replace("by" , "by:").replaceAll("\\s+$", "") + ")";
    }
}
