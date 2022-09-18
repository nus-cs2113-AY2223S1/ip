package duke.task;

import java.util.Map;

import duke.DukeException;
import duke.Parser;

/**
 * Instantiates tasks from input strings.
 */
public class TaskFactory {

    /**
     * Instantiates todo tasks, deadline tasks and event tasks from input strings.
     * 
     * @param description The user input string
     * @return The task created
     * @throws DukeException Throws an exception if the task does not match any
     *                       valid format
     */
    public static Task createTask(String description) throws DukeException {
        String keyword = Parser.parseKeyword(description);
        String name = Parser.parseName(description);
        Map<String, String> params = Parser.parseParams(description);
        switch (keyword) {
        case "deadline":
            return new DeadlineTask(name, params.get("by"), Boolean.parseBoolean(params.getOrDefault("done", "false")));
        case "event":
            return new EventTask(name, params.get("at"), Boolean.parseBoolean(params.getOrDefault("done", "false")));
        case "todo":
            return new TodoTask(name, Boolean.parseBoolean(params.getOrDefault("done", "false")));
        default:
            throw new DukeException("I don't know what that means :-(");
        }
    }
}
