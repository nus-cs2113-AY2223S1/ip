public class Event extends Task{
    public Event(String task) throws EmptyDescriptionException {
        // Call constructor for superclass and change formatting to brackets
        super(task.replace("/at", "(at:") + ")");
        // Print out event added
        System.out.println("  [E][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        // Overridden method to print [E] for event type of task
        System.out.println((index + 1) + ". [E][" + getStatusIcon() + "] " + getTask());
    }
}
