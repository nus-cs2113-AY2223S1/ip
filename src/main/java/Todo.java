public class Todo extends Task{

    public Todo(String task) {
        super(task);
        System.out.println("  [T][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        System.out.println((index + 1) + ". [T][" + getStatusIcon() + "] " + getTask());
    }
}
