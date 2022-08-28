public class Event extends Task{
    public Event(String task) {
        super(task.replace("/at", "(at: ") + ")");
        System.out.println("    [E][ ] " + getTask());
    }

    @Override
    public void printTask(int index) {
        System.out.println((index + 1) + ".[E][" + getStatusIcon() + "] " + getTask());
    }
}
