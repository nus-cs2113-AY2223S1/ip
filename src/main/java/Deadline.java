public class Deadline extends Task{
    /**
     * Creates Task Object
     *
     * @param text
     */
    protected String by;
    //protected String symbol;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        //symbol = "D";

        //String description = text.substring();
        //String numericString = line.substring(line.indexOf(" ")+1);

    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
