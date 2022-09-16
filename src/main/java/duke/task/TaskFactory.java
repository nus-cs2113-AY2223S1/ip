package duke.task;

import java.util.Map;

import duke.DukeException;
import duke.Parser;

public class TaskFactory {

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
