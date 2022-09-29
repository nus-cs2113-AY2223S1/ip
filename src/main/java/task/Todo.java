package task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
        System.out.println("  " + this);
    }

    public Todo(String description, boolean status) {
        super(description, status);
        System.out.println("  " + this);
    }


    public String toString() {
        return "[T]" + super.toString();
    }
}
