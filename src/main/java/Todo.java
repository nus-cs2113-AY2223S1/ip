public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public void print() {
        String printString = "[T][";
        if (super.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] " + super.description;
        System.out.println(printString);
    }
}
