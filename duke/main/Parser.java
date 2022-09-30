package duke.main;

import duke.error.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {
    static String FILE_SEPARATOR = "-";
    static String LINE_DIVIDER = "/";

    static int COMMAND_INDEX = 0;
    static int MARK_INDEX = 1;
    static int KEYWORD_INDEX = 1;
    static String COMMAND_WORD_LIST = "list";
    static String COMMAND_WORD_MARK = "mark";
    static String COMMAND_WORD_UNMARK = "unmark";
    static String COMMAND_WORD_TODO = "todo";
    static String COMMAND_WORD_DEADLINE = "deadline";
    static String COMMAND_WORD_EVENT = "event";
    static String COMMAND_WORD_DELETE = "delete";
    static String COMMAND_WORD_FIND = "find";
    public Parser() {}

    /**
     * Finds the entered task date from the user
     * @param line line of input from the user
     * @return The date of the task
     */
    public static String findTaskDate(String line) {
        int dateIndex = line.indexOf(LINE_DIVIDER);
        String taskDate = line.substring(dateIndex + 4);
        return taskDate;
    }

    /**
     * Finds the entered task name from the user
     * @param line line of input from the user
     * @return The name/description of the task
     */
    public static String findTaskName(String line) {
        int dateIndex = line.indexOf(LINE_DIVIDER);
        int spaceIndex = line.indexOf(" ");
        String taskName = "";
        if (dateIndex == -1) {
            taskName += line.substring(spaceIndex);
        } else {
            taskName += line.substring(spaceIndex, dateIndex);
        }
        return taskName;
    }

    /**
     * Handles commands from the user and passes inputs to TaskList methods
     * If the command involves tasks, passes it to the handleTask function
     * If Duke does not recognize the command, returns an error
     * @param line line of input from the user
     */
    public static void parse(String line) {
        String[] words = line.split(" ");
        String command = words[COMMAND_INDEX];
        if (command.matches(COMMAND_WORD_LIST)) {
            TaskList.list();
        } else if (command.matches(COMMAND_WORD_MARK)) {
            String markIndex = words[MARK_INDEX];
            int index = Integer.parseInt(markIndex);
            TaskList.markTask(index);
        } else if (command.matches(COMMAND_WORD_UNMARK)) {
            String markIndex = words[MARK_INDEX];
            int index = Integer.parseInt(markIndex);
            TaskList.unmarkTask(index);
        } else if (command.matches(COMMAND_WORD_DELETE)) {
            String markIndex = words[MARK_INDEX];
            int index = Integer.parseInt(markIndex);
            TaskList.delete(index);
        } else if (command.matches(COMMAND_WORD_FIND)) {
            String keyword = line.split(" ")[KEYWORD_INDEX];
            TaskList.find(keyword);
        } else if (command.matches(COMMAND_WORD_DEADLINE) || command.matches(COMMAND_WORD_EVENT)
                || command.matches(COMMAND_WORD_TODO)) {
            handleTask(line, command);
        } else {
            // Wrong command
            Ui.showCommandError();
            Ui.greet();
        }
    }

    /**
     * Passes input to checkSpace function
     * Handles the error if one is thrown
     * @param line line of input from the user
     * @return True if there are no errors
     */
    public static boolean taskSpaceCheck(String line) {
        try {
            Ui.checkSpace(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Don't forget to include your task!");
            return false;
        }
    }

    /**
     * Passes input to checkDivider function
     * Handles the error if one is thrown
     * @param line line of input from the user
     * @return True if there are no errors
     */
    public static boolean taskDividerCheck(String line) {
        try {
            Ui.checkDivider(line);
            return true;
        } catch (DukeException e) {
            System.out.println("Don't forget to include the deadline!");
            return false;
        }
    }

    /**
     * Handles task commands from the user
     * @param line line of input from the user
     * @param command command extracted from the line of input
     */
    public static void handleTask(String line, String command) {
        if (taskSpaceCheck(line)) {
            switch (command) {
            case "deadline":
                if (taskDividerCheck(line)) {
                    String taskName = findTaskName(line);
                    String taskDate = findTaskDate(line);
                    TaskList.addDeadline(taskName, taskDate);
                    break;
                }
            case "event":
                if (taskDividerCheck(line)) {
                    String taskName = findTaskName(line);
                    String taskDate = findTaskDate(line);
                    TaskList.addEvent(taskName, taskDate);
                    break;
                }
            case "todo":
                String taskName = findTaskName(line);
                TaskList.addTodo(taskName);
                break;
            }
        }
        Storage.save();
    }

    /**
     * Handles the saved data in duke.txt
     * @param line line of saved input
     */
    public static void handleFile(String line) {
        String[] words = line.split(FILE_SEPARATOR);
        String taskType = words[0];
        String taskName = words[1];
        if (taskType.matches("T")) {
            TaskList.addTodo(taskName);
        } else if (taskType.matches("E")) {
            String taskDate = words[2];
            TaskList.addEvent(taskName, taskDate);
        } else if (taskType.matches("D")) {
            String taskDate = words[2];
            TaskList.addDeadline(taskName, taskDate);
        }
    }
}
