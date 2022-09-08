package task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
        System.out.println("  " + this);
    }


    public String toString() {
        return "[T]" + super.toString();
    }
}
