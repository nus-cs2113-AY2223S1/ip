package duke;

import java.util.Map;
import java.util.TreeMap;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.FindCommand;

public class Parser {

    public static Command parseCommand(String description) throws DukeException {
        String keyword = parseKeyword(description);
        switch (keyword) {
        case "bye":
            return new ExitCommand(description);
        case "list":
            return new ListCommand(description);
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "deadline":
        case "event":
        case "todo":
            return new AddCommand(description);
        case "find":
            return new FindCommand(description);
        default:
            throw new DukeException("I don't understand this command!");
        }
    }

    public static String parseKeyword(String description) {
        return description.split(" ")[0];
    }

    public static String removeKeyword(String description) {
        int firstSpace = description.indexOf(' ');
        if (firstSpace == -1) {
            return "";
        }
        String withoutKeyword = description.substring(firstSpace + 1);
        return withoutKeyword;
    }

    public static String parseName(String description) {
        String withoutKeyword = removeKeyword(description);
        int firstSlash = withoutKeyword.indexOf('/');
        if (firstSlash != -1) {
            String withoutParams = withoutKeyword.substring(0, firstSlash - 1);
            return withoutParams.trim();
        }
        return withoutKeyword.trim();
    }

    public static Map<String, String> parseParams(String description) {
        Map<String, String> paramsMap = new TreeMap<>();
        int firstSlash = description.indexOf('/');
        if (firstSlash == -1) {
            return paramsMap;
        }
        String paramsString = description.substring(firstSlash + 1);
        for (String param : paramsString.split(" /")) {
            int firstSpace = param.indexOf(' ');
            String key = param.substring(0, firstSpace).trim();
            String value = param.substring(firstSpace + 1).trim();
            paramsMap.put(key, value);
        }
        return paramsMap;
    }
}
