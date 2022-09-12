package duke;
public class Event extends Task{

    protected String date;
    protected final String SYMBOL = new String("E");

    public Event(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String print() {
        return ("[" + this.getSYMBOL() + "]" + super.print() + " (at: " + this.date + ")");
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