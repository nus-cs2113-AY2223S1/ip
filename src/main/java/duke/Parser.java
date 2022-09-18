package duke;

public class Parser {

    public static String parse(String fullCommand) {
        String[] items = fullCommand.split(" ");
        return items[0];
    }

    public static int getTaskID(String command) throws DukeException{
        command = command.trim();
        String[] words = command.split(" ");
        if (words.length == 1) {
            throw new DukeException("TaskID missing");
        }
        return Integer.parseInt(words[1]);
    }

    //remove the first word
    public static String removeTaskType(String command) throws DukeException{
        int indexOfFirstBlankSpace = command.indexOf(" ");
        String firstWord;
        if (indexOfFirstBlankSpace == -1) {
            firstWord = command;
        } else {
            firstWord = command.substring(0, indexOfFirstBlankSpace);
        }
        if (indexOfFirstBlankSpace == -1 && (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event"))) {
            throw new DukeException(":( OOPS!!! The description of a " + command + " cannot be empty.");
        } else if (!firstWord.equals("todo") && !firstWord.equals("deadline") && !firstWord.equals("event")) {
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        command = command.substring(indexOfFirstBlankSpace + 1);
        return command;
    }

    public static String getTaskName(String command) {
        int indexOfBackslash = command.indexOf('/');
        return command.substring(0, indexOfBackslash-1);
    }

    public static String getTaskDateTime(String command) {
        int indexOfBackslash = command.indexOf('/');
        command = command.substring(indexOfBackslash + 1);
        int indexOfFirstBlankspace = command.indexOf(' ');
        return command.substring(indexOfFirstBlankspace + 1);
    }

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
