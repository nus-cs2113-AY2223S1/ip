public class Deadline extends Task{
    public Deadline(String task) {
        // Call constructor of super class and change formatting to braces
        super(task.replace("/by", "(by:") + ")");
        // Print out deadline added
        System.out.println("  [D][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        // Overridden method to print deadlines with [D]
        System.out.println((index + 1) + ". [D][" + getStatusIcon() + "] " + getTask());
    }
}
