package duke;

import duke.exceptions.DukeException;

import java.io.IOException;

/**
 * The class Parser has methods to handle the parsing of input strings from the user
 */
public class Parser {
    /**
     * @param line Input string from user
     * @return a boolean value true if the string contains "bye", false if it does not
     */
    static boolean checkBye(String line) {
        return line.equals("bye");
    }

    /**
     * @param line Input string from user
     * @return the command from the user
     */
    static String getAction(String line) {
        return line.split(" ")[0];
    }

    /**
     * @param Manager The initialized TaskManager object that handles and contains the task objects
     * @param line    Input string from user
     * @throws DukeException An exception thrown when the command from the user does not match any
     *                       acceptable commands
     * @throws IOException   An exception thrown when there is an error during the read and write to
     *                       file during the handling of commands from user
     */
    static void parse(TaskManager Manager, String line) {
        String action = getAction(line);
        try {
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
            case "find":
                Manager.findTasks(line);
                break;
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Nothing makes any sense");
        } catch (IOException e) {
            System.out.println("Save file corrupted! Abort mission!");
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

    public static String parseMark(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    public static String parseUnmark(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    public static String parseDelete(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    public static String parseFind(String line) {
        String[] result = line.split(" ");
        return result[1];
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
