package duke.util.asset;

public class Todo extends Task {

    public static final String COMMAND = "todo";

    public Todo(String description) {
        super(description);
        this.addMessage = "HELLO BEEP, added a new ToDo: " + description;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return COMMAND + " " + getTask();
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }
}