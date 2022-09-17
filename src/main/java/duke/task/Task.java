package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";

    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Converts a Task object to a string.
     *
     * @param task Object to be converted.
     * @return String representation of the converted object.
     */
    public static String convertToString(Task task) {
        String taskStr = "";

        if (task instanceof Todo) {
            taskStr += "T";
        } else if (task instanceof Deadline) {
            taskStr += "D";
        } else if (task instanceof Event) {
            taskStr += "E";
        } else {
            taskStr += " ";
        }

        taskStr += " | ";

        if (task.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }

        taskStr += " | ";

        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            taskStr += todo.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            taskStr += deadline.getDescription();
            taskStr += " | ";
            String by = deadline.by.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            taskStr += by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            taskStr += event.getDescription();
            taskStr += " | ";
            String startAt = event.startAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            String endAt = event.endAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            taskStr += startAt + " " + endAt;
        } else {
            taskStr += task.getDescription();
        }

        return taskStr;
    }

    /**
     * Converts a string into a Task object.
     *
     * @param taskStr String representation of the object to be converted.
     * @return Task object from the converted string.
     */
    public static Task convertFromString(String taskStr) {
        if (taskStr.isEmpty()) {
            return null;
        }

        String[] taskStrArr = taskStr.split(" \\| ");
        String type = taskStrArr[0].trim();
        String isCompleteStr = taskStrArr[1].trim();
        boolean isComplete = isCompleteStr.equals("1");
        String description = taskStrArr[2].trim();

        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            todo.setComplete(isComplete);
            return todo;
        case "D":
            String by = taskStrArr[3].trim();
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            Deadline deadline = new Deadline(description, byDateTime);
            deadline.setComplete(isComplete);
            return deadline;
        case "E":
            String at = taskStrArr[3].trim();
            String[] atArray = at.split(" ");
            String startAt = atArray[0] + " " + atArray[1];
            String endAt = atArray[2] + " " + atArray[3];
            LocalDateTime startAtDateTime = LocalDateTime.parse(startAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            LocalDateTime endAtDateTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            Event event = new Event(description, startAtDateTime, endAtDateTime);
            event.setComplete(isComplete);
            return event;
        default:
            Task task = new Task(description);
            task.setComplete(isComplete);
            return task;
        }
    }

    public void print() {
        String printString = "[";

        if (this instanceof Todo) {
            printString += "T";
        } else if (this instanceof Deadline) {
            printString += "D";
        } else if (this instanceof Event) {
            printString += "E";
        } else {
            printString += " ";
        }

        printString += "][";

        if (isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }

        printString += "] " + description;

        if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            String by = deadline.by.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            printString += " (by: " + by + ")";
        } else if (this instanceof Event) {
            Event event = (Event) this;
            String startAt = event.startAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            String endAt = event.endAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
            printString += " (at: " + startAt + " to " + endAt + ")";
        } else {
            printString += "";
        }
        System.out.println(printString);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
