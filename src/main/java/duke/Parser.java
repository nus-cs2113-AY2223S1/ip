package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Parse the input.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Converts task in string format to object.
     *
     * @param fileContent Tasks to be converted.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> decodeTaskListFromFile(ArrayList<String> fileContent) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : fileContent) {
            ArrayList<String> commandWordAndParameters = parsedCommand(line);
            decodeTaskFromString(tasks, commandWordAndParameters);
        }
        return tasks;
    }

    private void decodeTaskFromString(ArrayList<Task> tasks, ArrayList<String> commandWordAndParameters) throws DukeException {
        String taskType  = getTaskType(commandWordAndParameters);
       Command command = new Command();
        switch (taskType) {
        case ("T"):
            ToDo todo = command.createToDo(commandWordAndParameters);
            tasks.add(todo);
            break;
        case ("D"):
            Deadline deadline = command.createDeadline(commandWordAndParameters);
            tasks.add(deadline);
            break;
        case ("E"):
            Event event = command.createEvent(commandWordAndParameters);
            tasks.add(event);
            break;
        default:
            throw new DukeException("Error reading command");
        }
    }


    private String getTaskType(ArrayList<String> words) {
        return words.get(0);
    }


    private ArrayList<String> parsedCommand(String line) {
        ArrayList<String> words = new ArrayList<>();
        int separatorIndex = line.indexOf("|");
        while (separatorIndex != -1) {
            words.add(line.substring(0, separatorIndex).trim());
            line = line.substring(separatorIndex + 1).trim();
            separatorIndex = line.indexOf("|");
        }
        words.add(line);
        return words;
    }

    /**
     * Separates command type and command arguments.
     *
     * @param input Input from user.
     * @return Separated command type and arguments.
     */
    public String[] splitCommandTypeAndArguments(String input) {
        return input.split(" ", 2);
    }

    /**
     * Returns an array of separated task name and date with time.
     *
     * @param commandArguments Command arguments given by the user.
     * @param separator Separator to split the string.
     * @return Array of description and date with time.
     */
   public String[] getDescriptionAndTime(String commandArguments, String separator) {
       return commandArguments.split(separator);
   }


    /**
     * Returns command arguments from an array.
     *
     * @param commandTypeAndArguments Array containing command type and command arguments
     * @return command arguments
     */
    public String getCommandArguments(String[] commandTypeAndArguments) {
        if (commandTypeAndArguments.length == 1) {
            return "";
        }
        return commandTypeAndArguments[1];
    }


    public String getCommand(String[] commandTypeAndArguments) {
        return commandTypeAndArguments[0];
    }
}
