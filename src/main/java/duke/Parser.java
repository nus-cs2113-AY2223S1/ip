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
     * @return Task objects.
     */
    public ArrayList<Task> decodeTaskListFromFile(ArrayList<String> fileContent) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : fileContent) {
            ArrayList<String> commandWordAndParameters = parsedCommand(line);
            decodeTaskFromString(tasks, commandWordAndParameters);
        }
        return tasks;
    }

    private void decodeTaskFromString(ArrayList<Task> tasks, ArrayList<String> commandWordAndParameters) {
        String taskType  = getTaskType(commandWordAndParameters);
        switch (taskType) {
        case ("T"):
            ToDo todo = createToDoAndUpdateStatus(commandWordAndParameters);
            tasks.add(todo);
            break;
        case ("D"):
            Deadline deadline = createDeadlineAndUpdateStatus(commandWordAndParameters);
            tasks.add(deadline);
            break;
        case ("E"):
            Event event = createEventAndUpdateStatus(commandWordAndParameters);
            tasks.add(event);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    private Event createEventAndUpdateStatus(ArrayList<String> words) {
        Event event = new Event(words.get(2), words.get(3));
        if (words.get(1).equals("1")) {
            event.setDone(true);
        }
        return event;
    }

    private Deadline createDeadlineAndUpdateStatus(ArrayList<String> words) {
        Deadline deadline = new Deadline(words.get(2), words.get(3));
        if (words.get(1).equals("1")) {
            deadline.setDone(true);
        }
        return deadline;
    }

    private ToDo createToDoAndUpdateStatus(ArrayList<String> words) {
        ToDo todo = new ToDo(words.get(2));
        if (words.get(1).equals("1")) {
            todo.setDone(true);
        }
        return todo;
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


}
