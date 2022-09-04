public class Todo extends Task{

    public Todo(String task) throws EmptyDescriptionException {
        // Call constructor of superclass and print ou task added
        super(task);
        System.out.println("  [T][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        //Overridden method to print [T] for todo task
        System.out.println((index + 1) + ". [T][" + getStatusIcon() + "] " + getTask());
    }
}
