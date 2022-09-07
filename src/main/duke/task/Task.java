package main.duke.task;

enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
}

public class Task {

    private String mark;
    private String description;
    private TaskType type;

    public Task(String description) {
        this.description = description;
        this.mark = " ";
        this.type = TaskType.TODO;
    }

    public Task(String description, TaskType type) {
        this.description = description;
        this.mark = " ";
        this.type = type;
    }

    public void mark() {
        setMark("X");
    }

    public void unmark() {
        setMark(" ");
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String typeLetter = " ";
        switch (this.type) {
        case TODO:
            typeLetter = "T";
            break;
        case DEADLINE:
            typeLetter = "D";
            break;
        case EVENT:
            typeLetter = "E";
            break;
        }
        return "[" + typeLetter + "]["
                + getMark() + "] "
                + getDescription();
    }
}
