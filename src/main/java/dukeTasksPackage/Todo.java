package dukeTasksPackage;

import dukeTasksPackage.Task;

public class Todo extends Task {

    public Todo(String description) {
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "   [T]" + " [" + status + "] " + description;
    }

    public String toFileString() {
        return "T | " + status + " | " + description + System.lineSeparator();
    }
}

