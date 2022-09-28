package duke.util.asset;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.addMessage = "HELLO BEEP, added a new ToDo: " + description;
        this.command = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return command + " " + getTask();
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }
}