public class Deadline extends Task{
    public Deadline(String task) {
        super(task.replace("/by", "(by:") + ")");
        System.out.println("  [D][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        System.out.println((index + 1) + ". [D][" + getStatusIcon() + "] " + getTask());
    }
}
