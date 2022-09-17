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

    private static String convertTodoToString(Todo todo) {
        String taskStr = "T | ";
        if (todo.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += todo.description;

        return taskStr;
    }

    private static String convertDeadlineToString(Deadline deadline) {
        String taskStr = "D | ";
        if (deadline.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += deadline.description;
        taskStr += " | ";
        taskStr += deadline.by.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));

        return taskStr;
    }

    private static String convertEventToString(Event event) {
        String taskStr = "E | ";
        if (event.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += event.description;
        taskStr += " | ";
        taskStr += event.startAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        taskStr += " ";
        taskStr += event.endAt.format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));

        return taskStr;
    }

    private static String convertTaskToString(Task task) {
        String taskStr = " | ";
        if (task.isComplete) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += task.description;

        return taskStr;
    }

    /**
     * Converts a Task object to a string.
     *
     * @param task Object to be converted.
     * @return String representation of the converted object.
     */
    public static String convertToString(Task task) {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            return convertTodoToString(todo);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return convertDeadlineToString(deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return convertEventToString(event);
        } else {
            return convertTaskToString(task);
        }
    }

    private static Task convertFromStringToTodo(String isCompleteStr, String description) {
        Todo todo = new Todo(description);
        todo.setComplete(isCompleteStr.equals("1"));
        return todo;
    }

    private static Task convertFromStringToDeadline(String isCompleteStr, String description, String byStr) {
        LocalDateTime byDateTime = LocalDateTime.parse(byStr, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        Deadline deadline = new Deadline(description, byDateTime);
        deadline.setComplete(isCompleteStr.equals("1"));
        return deadline;
    }

    private static Task convertFromStringToEvent(String isCompleteStr, String description, String atStr) {
        String[] atArray = atStr.split(" ");
        String startAt = atArray[0] + " " + atArray[1];
        String endAt = atArray[2] + " " + atArray[3];

        LocalDateTime startAtDateTime = LocalDateTime.parse(startAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        LocalDateTime endAtDateTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        Event event = new Event(description, startAtDateTime, endAtDateTime);
        event.setComplete(isCompleteStr.equals("1"));
        return event;
    }

    private static Task convertFromStringToTask(String isCompleteStr, String description) {
        Task task = new Task(description);
        task.setComplete(isCompleteStr.equals("1"));
        return task;
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
        String description = taskStrArr[2].trim();

        if (type.equals("T")) {
            return convertFromStringToTodo(isCompleteStr, description);
        } else if (type.equals("D")) {
            String by = taskStrArr[3].trim();
            return convertFromStringToDeadline(isCompleteStr, description, by);
        } else if (type.equals("E")) {
            String at = taskStrArr[3].trim();
            return convertFromStringToEvent(isCompleteStr, description, at);
        } else {
            return convertFromStringToTask(isCompleteStr, description);
        }
    }

    private void printTodo() {
        Todo todo = (Todo) this;

        String printString = "[T][";
        if (todo.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] " + todo.description;

        System.out.println(printString);
    }

    private void printDeadline() {
        Deadline deadline = (Deadline) this;

        String printString = "[D][";
        if (deadline.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += deadline.description;
        printString += " (by: ";
        printString += deadline.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += ")";

        System.out.println(printString);
    }

    private void printEvent() {
        Event event = (Event) this;

        String printString = "[E][";
        if (event.isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += event.description;
        printString += " (at: ";
        printString += event.startAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += " to ";
        printString += event.endAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        printString += ")";

        System.out.println(printString);
    }

    private void printTask() {
        String printString = "[ ][";
        if (isComplete) {
            printString += "X";
        } else {
            printString += " ";
        }
        printString += "] ";
        printString += description;

        System.out.println(printString);
    }

    public void print() {
        if (this instanceof Todo) {
            printTodo();
        } else if (this instanceof Deadline) {
            printDeadline();
        } else if (this instanceof Event) {
            printEvent();
        } else {
            printTask();
        }
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
