package duke.storage;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings({"PatternVariableCanBeUsed", "StringConcatenationInLoop"})
public class LocalStorage {
    private static String convertTodoToStorageSafeFormat(Todo todo) {
        String taskStr = "T | ";
        if (todo.isComplete()) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += todo.getDescription();

        return taskStr;
    }

    private static String convertDeadlineToStorageSafeFormat(Deadline deadline) {
        String taskStr = "D | ";
        if (deadline.isComplete()) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += deadline.getDescription();
        taskStr += " | ";
        taskStr += deadline.getBy().format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));

        return taskStr;
    }

    private static String convertEventToStorageSafeFormat(Event event) {
        String taskStr = "E | ";
        if (event.isComplete()) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += event.getDescription();
        taskStr += " | ";
        taskStr += event.getStartAt().format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        taskStr += " ";
        taskStr += event.getEndAt().format(DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));

        return taskStr;
    }

    private static String convertTaskToStorageSafeFormat(Task task) {
        String taskStr = " | ";
        if (task.isComplete()) {
            taskStr += "1";
        } else {
            taskStr += "0";
        }
        taskStr += " | ";
        taskStr += task.getDescription();

        return taskStr;
    }

    /**
     * Converts a Task object to a storage-safe formatted string.
     *
     * @param task Object to be converted.
     * @return String representation of the converted object.
     */
    public static String convertToStorageSafeFormat(Task task) {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;

            return convertTodoToStorageSafeFormat(todo);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;

            return convertDeadlineToStorageSafeFormat(deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;

            return convertEventToStorageSafeFormat(event);
        } else {
            return convertTaskToStorageSafeFormat(task);
        }
    }

    private static Task convertFromStorageSafeFormatToTodo(String isCompleteStr, String description) {
        Todo todo = new Todo(description);
        todo.setComplete(isCompleteStr.equals("1"));

        return todo;
    }

    private static Task convertFromStorageSafeFormatToDeadline(String isCompleteStr, String description, String byStr) {
        LocalDateTime byDateTime = LocalDateTime.parse(byStr, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        Deadline deadline = new Deadline(description, byDateTime);
        deadline.setComplete(isCompleteStr.equals("1"));

        return deadline;
    }

    private static Task convertFromStorageSafeFormatToEvent(String isCompleteStr, String description, String atStr) {
        String[] atArray = atStr.split(" ");
        String startAt = atArray[0] + " " + atArray[1];
        String endAt = atArray[2] + " " + atArray[3];

        LocalDateTime startAtDateTime = LocalDateTime.parse(startAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        LocalDateTime endAtDateTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern(Task.DATE_TIME_FORMAT));
        Event event = new Event(description, startAtDateTime, endAtDateTime);
        event.setComplete(isCompleteStr.equals("1"));

        return event;
    }

    private static Task convertFromStorageSafeFormatToTask(String isCompleteStr, String description) {
        Task task = new Task(description);
        task.setComplete(isCompleteStr.equals("1"));

        return task;
    }

    /**
     * Converts a formatted string into a Task object.
     *
     * @param taskStr String representation of the object to be converted.
     * @return Task object from the converted string.
     */
    public static Task convertFromStorageSafeFormat(String taskStr) {
        if (taskStr.isEmpty()) {
            return null;
        }

        String[] taskStrArr = taskStr.split(" \\| ");
        String type = taskStrArr[0].trim();
        String isCompleteStr = taskStrArr[1].trim();
        String description = taskStrArr[2].trim();

        //noinspection IfCanBeSwitch
        if (type.equals("T")) {
            return convertFromStorageSafeFormatToTodo(isCompleteStr, description);
        } else if (type.equals("D")) {
            String by = taskStrArr[3].trim();

            return convertFromStorageSafeFormatToDeadline(isCompleteStr, description, by);
        } else if (type.equals("E")) {
            String at = taskStrArr[3].trim();

            return convertFromStorageSafeFormatToEvent(isCompleteStr, description, at);
        } else {
            return convertFromStorageSafeFormatToTask(isCompleteStr, description);
        }
    }

    /**
     * Save tasks in memory to local storage.
     *
     * @param tasks List of tasks to save.
     * @param path Path of the directory to save.
     * @param filename Filename of the tasks file.
     * @throws IOException If there was an error writing to local storage.
     */
    public static void saveTasks(ArrayList<Task> tasks, String path, String filename) throws IOException {
        Path tasksDirectoryPath = Paths.get(path);
        Path tasksFilePath = Paths.get(path + filename);

        if (Files.notExists(tasksDirectoryPath)) {
            Files.createDirectories(tasksDirectoryPath);
        }

        String tasksStr = "";
        for (Task task : tasks) {
            tasksStr += convertToStorageSafeFormat(task);
            tasksStr += "\n";
        }

        Files.writeString(tasksFilePath, tasksStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    /**
     * Read tasks from local storage to memory.
     *
     * @param path Path of the directory to load.
     * @param filename Filename of the tasks file.
     * @return List of tasks that was loaded.
     * @throws IOException If there was an error reading from local storage.
     */
    public static ArrayList<Task> loadTasks(String path, String filename) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        Path tasksFilePath = Paths.get(path + filename);

        String tasksStr = Files.readString(tasksFilePath);

        String[] tasksStrArr = tasksStr.split("\n");
        for (String taskStr : tasksStrArr) {
            Task task = convertFromStorageSafeFormat(taskStr);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }
}
