public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void print() {
        String printString = "[E][";
        if (super.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] " + super.description + " (at: " + at + ")";
        System.out.println(printString);
    }
}
