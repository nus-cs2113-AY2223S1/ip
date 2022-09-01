public class TodoTask extends Task{
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        System.out.println("[T][" + getMarking() + "] " + getDescription());
    }

}
