public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void print() {
        String printString = "[D][";
        if (super.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] " + super.description + " (by: " + by + ")";
        System.out.println(printString);
    }
}
