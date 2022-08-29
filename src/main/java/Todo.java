public class Todo extends Task{
    /**
     * Creates Task Object
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }



}
