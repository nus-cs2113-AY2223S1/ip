package main.duke.task;

/** Separates the types of tasks into an enum class */
enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
}

/** Master class for creating a task */
public class Task {

    private String mark;
    private String description;
    private TaskType type;

    /** Constructor for creating a Task and initializing the variables */
    public Task(String description) {
        this.description = description;
        this.mark = " ";
        this.type = TaskType.TODO;
    }

    /** Constructor for creating a Task with a given type */
    public Task(String description, TaskType type) {
        this.description = description;
        this.mark = " ";
        this.type = type;
    }

    /** Marks the task as complete by setting the mark variable */
    public void mark() {
        setMark("X");
    }

    /** Marks the task as incomplete by setting the mark variable */
    public void unmark() {
        setMark(" ");
    }

    /** Getter for the mark variable */
    public String getMark() {
        return mark;
    }

    /** Setter for the mark variable */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /** Getter for the description variable */
    public String getDescription() {
        return description;
    }

    /** Overrides to the toString method to correctly categorize each Task */
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

    /** Returns the data format of the Task */
    public String dataString() {
        return toString();
    }
}
