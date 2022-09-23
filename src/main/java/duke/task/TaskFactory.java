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
        case DeadlineTask.KEYWORD:
            return new DeadlineTask(name, params.get(DeadlineTask.BY_PARAM),
                    Boolean.parseBoolean(params.getOrDefault(Task.DONE_PARAM, Boolean.toString(false))));
        case EventTask.KEYWORD:
            return new EventTask(name, params.get(EventTask.AT_PARAM),
                    Boolean.parseBoolean(params.getOrDefault(Task.DONE_PARAM, Boolean.toString(false))));
        case TodoTask.KEYWORD:
            return new TodoTask(name,
                    Boolean.parseBoolean(params.getOrDefault(Task.DONE_PARAM, Boolean.toString(false))));
        default:
            throw new DukeException(DukeException.getDidNotUnderstandMessage());
        }
    }
}
