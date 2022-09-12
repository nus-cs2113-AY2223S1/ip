package duke;
public class Deadline extends Task{

    protected String date;
    protected final String SYMBOL = new String("D");

    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String print() {
        return ("[" + this.getSYMBOL() + "]" + super.print() + " (by: " + this.date + ")");
    }

    @Override
    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String s = new String(SYMBOL + "|" + isDoneStr + "|" + this.description + "|" + this.date + "\n");
        return s;
    }

    public String getDate() {
        return this.date;
    }
}