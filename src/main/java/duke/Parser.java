package duke;

import duke.exceptions.DukeException;

import java.io.IOException;

public class Parser {
    static boolean checkBye(String line) {
        return line.equals("bye");
    }

    static String getAction(String line){
        return line.split(" ")[0];
    }
    static void parse(TaskManager Manager,String line) throws DukeException, IOException {
        String action = getAction(line);
        switch (action) {
        case "mark":
            Manager.markTasks(line);
            break;
        case "unmark":
            Manager.unmarkTasks(line);
            break;
        case "list":
            Manager.printTasks();
            break;
        case "todo":
            Manager.storeTodo(line);
            break;
        case "deadline":
            Manager.storeDeadline(line);
            break;
        case "event":
            Manager.storeEvent(line);
            break;
        case "delete":
            Manager.deleteTasks(line);
            break;
        default:
            throw new DukeException();
        }
    }

    public static String parseTodo(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    public static String parseDeadline(String line) {
        String[] result = line.split("/by ");
        return result[0].substring(9);
    }

    public static String parseDeadlineDate(String line) {
        String[] result = line.split("/by ");
        return result[1];
    }

    public static String parseEvent(String line) {
        String[] result = line.split("/at ");
        return result[0].substring(6);
    }

    public static String parseEventDate(String line) {
        String[] result = line.split("/at ");
        return result[1];
    }

    public static String parseSaveDeadline(String line) {
        String[] result = line.split("/by ");
        return result[0].substring(7);
    }

    public static String parseSaveEvent(String line) {
        String[] result = line.split("/at ");
        return result[0].substring(7);
    }

    public static String parseSaveTodo(String line) {
        String taskDescription = line.substring(7);
        return taskDescription;
    }
    public static String parseSaveTaskType(String line) {
        String taskType = line.substring(0, 2);
        taskType = taskType.replace("[", "");
        taskType = taskType.replace("]", "");
        return taskType;
    }

    public static Boolean parseSaveTaskStatus(String line) {
        String taskStatus = line.substring(3, 5);
        taskStatus = taskStatus.replace("[", "");
        taskStatus = taskStatus.replace("]", "");
        if (taskStatus.equals("X"))
            return true;
        else
            return false;
    }

}
