package duke;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Returns user's primary command eg. list, mark, delete
     * @param command Entire user input.
     * @return User's primary command.
     */
    public static String parse(String command) {
        String[] items = command.split(" ");
        return items[0];
    }

    /**
     * Returns user's input task ID (1-indexed).
     * @param command Entire user input.
     * @return Task ID.
     * @throws DukeException If user did not input task ID.
     */
    public static int getTaskID(String command) throws DukeException{
        command = command.trim();
        String[] words = command.split(" ");
        if (words.length == 1) {
            throw new DukeException("Task ID missing");
        }
        return Integer.parseInt(words[1]);
    }

    /**
     * Removes the first word (todo, deadline, event) and returns the rest of the string.
     * @param command Entire user input.
     * @return Entire user input, excluding the first word.
     * @throws DukeException If user input is invalid or user did not input task description.
     */
    public static String removeTaskType(String command) throws DukeException{
        int indexOfFirstBlankSpace = command.indexOf(" ");
        String firstWord;
        if (indexOfFirstBlankSpace == -1) {
            firstWord = command;
        } else {
            firstWord = command.substring(0, indexOfFirstBlankSpace);
        }
        if (indexOfFirstBlankSpace == -1 && (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event"))) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
        command = command.substring(indexOfFirstBlankSpace + 1);
        return command;
    }

    /**
     * Returns task name.
     * @param command Entire user input, after removing the first word.
     * @return Task name.
     */
    public static String getTaskName(String command) {
        int indexOfBackslash = command.indexOf('/');
        return command.substring(0, indexOfBackslash-1);
    }

    /**
     * Returns task datetime, for deadline and event tasks.
     * @param command Entire user input, after removing the first word.
     * @return Task datetime.
     */
    public static String getTaskDateTime(String command) {
        int indexOfBackslash = command.indexOf('/');
        command = command.substring(indexOfBackslash + 1);
        int indexOfFirstBlankspace = command.indexOf(' ');
        return command.substring(indexOfFirstBlankspace + 1);
    }

    /**
     * Converts the list of tasks into a string to be stored into the file.
     * Makes use of ';' to separate task's type, status, name and datetime.
     * @param tasks List of tasks.
     * @param taskCounter Number of tasks.
     * @return List of tasks in a valid string format.
     */
    public static String convertTaskListIntoFileContent(TaskList tasks, int taskCounter) {
        String taskList = "";
        for (int i = 0; i < taskCounter; ++i) {
            char taskType = tasks.getTasks().get(i).getType();
            taskList += tasks.getTasks().get(i).getType() + " ; ";
            taskList += (tasks.getTasks().get(i).isDone() ? "1" : "0") + " ; ";
            switch(taskType) {
            case 'T':
                taskList += tasks.getTasks().get(i).getName() + "\n";
                break;
            case 'D':
            case 'E':
                taskList += tasks.getTasks().get(i).getName() + " ; " + tasks.getTasks().get(i).getDateTime() + "\n";
                break;
            default:
                Ui.showInvalidFormat();
            }
        }
        return taskList;
    }
}
