package duke;
public class Todo extends Task{

    protected final String SYMBOL = new String("T");

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String print() {
        return ("[" + this.getSYMBOL() + "]" + super.print());
    }

    @Override
    public String printToFile() {
        String isDoneStr = new String((this.isDone) ? "1" : "0");
        String s = new String(SYMBOL + "|" + isDoneStr + "|" + this.description + "\n");
        return s;
    }
}