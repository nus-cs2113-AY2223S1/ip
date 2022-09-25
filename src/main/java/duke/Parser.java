package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

import java.util.ArrayList;

public abstract class Parser {
    public static ArrayList<Task> parseTaskData(ArrayList<String> taskDataItems) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : taskDataItems) {
            String taskType = getTaskType(line);
            String taskStatus = getTaskStatus(line);
            String taskParameters = getTaskParameters(line);
            String taskDescription = getTaskDescription(taskParameters);
            String taskDatetime = "";

            switch (taskType) {
            case Todo.ICON:
                Todo todoTask = new Todo(taskDescription, taskStatus);
                tasks.add(todoTask);
                break;
            case Event.ICON:
                taskDatetime = getTaskDatetime(taskParameters);
                Event event = new Event(taskDescription, taskDatetime, taskStatus);
                tasks.add(event);
                break;
            case Deadline.ICON:
                taskDatetime = getTaskDatetime(taskParameters);
                Deadline deadline = new Deadline(taskDescription, taskDatetime, taskStatus);
                tasks.add(deadline);
                break;
            default:
                // Skip the line when it is an undefined task
                break;
            }
        }

        return tasks;
    }

    private static String getTaskType(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[0];
    }

    private static String getTaskStatus(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR)[1];
    }

    private static String getTaskParameters(String line) {
        return line.split(TaskList.FILE_STRING_SEPARATOR, 3)[2];
    }

    private static String getTaskDescription(String taskParameters) {
        return taskParameters.split(TaskList.FILE_STRING_SEPARATOR)[0];
    }

    private static String getTaskDatetime(String taskParameters) {
        return taskParameters.split(TaskList.FILE_STRING_SEPARATOR)[1];
    }
}
