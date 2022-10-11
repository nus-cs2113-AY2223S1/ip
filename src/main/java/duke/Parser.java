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

    /**
     * parses user input line to get description of todo
     * @param line the user inputted
     * @return the description of the todo
     */
    public static String parseTodo(String line) {
        String[] result = line.split(" ",2);
        return result[1];
    }

    /**
     * parses user input line to get description of deadline
     * @param line the user inputted
     * @return the description of the deadline
     */
    public static String parseDeadline(String line) {
        String[] result = line.split("/by ");
        return result[0].substring(9);
    }

    /**
     * parses user input line to get time of deadline
     * @param line the user inputted
     * @return the time of the deadline
     */
    public static String parseDeadlineDate(String line) {
        String[] result = line.split("/by ");
        return result[1];
    }

    /**
     * parses user input line to get description of event
     * @param line the user inputted
     * @return the description of the event
     */
    public static String parseEvent(String line) {
        String[] result = line.split("/at ");
        return result[0].substring(6);
    }

    /**
     * parses user input line to get time of event
     * @param line the user inputted
     * @return the time of the event
     */
    public static String parseEventDate(String line) {
        String[] result = line.split("/at ");
        return result[1];
    }

    /**
     * parses user input line to get position of task to be marked
     * @param line the user inputted
     * @return the position
     */
    public static String parseMark(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    /**
     * parses user input line to get position of task to be unmarked
     * @param line the user inputted
     * @return the position
     */
    public static String parseUnmark(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    /**
     * parses user input line to get position of task to be deleted
     * @param line the user inputted
     * @return the position
     */
    public static String parseDelete(String line) {
        String[] result = line.split(" ");
        return result[1];
    }

    /**
     * parses user input line to get text that the user wants to search among the existing tasks
     * @param line the user inputted
     * @return the search text
     */
    public static String parseFind(String line) {
        String[] result = line.split(" ",2);
        return result[1];
    }


    /**
     * parses line in save file to get description of deadline
     * @param line the saved line
     * @return the description of the deadline
     */
    public static String parseSaveDeadline(String line) {
        String[] result = line.split("/by ");
        return result[0].substring(7);
    }

    /**
     * parses line in save file to get description of event
     * @param line the saved line
     * @return the description of the event
     */
    public static String parseSaveEvent(String line) {
        String[] result = line.split("/at ");
        return result[0].substring(7);
    }

    /**
     * parses line in save file to get description of todo
     * @param line the saved line
     * @return the description of the todo
     */
    public static String parseSaveTodo(String line) {
        String taskDescription = line.substring(7);
        return taskDescription;
    }

    /**
     * parses line in save file to get type of saved task
     * @param line the saved line
     * @return the task type of the task
     */
    public static String parseSaveTaskType(String line) {
        String taskType = line.substring(0, 2);
        taskType = taskType.replace("[", "");
        taskType = taskType.replace("]", "");
        return taskType;
    }

    /**
     * parses line in save file to get status of saved task
     * @param line the saved line
     * @return the status of the task
     */
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
