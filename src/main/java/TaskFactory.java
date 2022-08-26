import java.util.Map;
import java.util.TreeMap;

public class TaskFactory {
    private static String parseKeyword(String description) {
        return description.split(" ")[0];
    }

    private static String parseName(String description) {
        int firstSpace = description.indexOf(' ');
        String withoutKeyword = description.substring(firstSpace + 1);
        int firstSlash = withoutKeyword.indexOf('/');
        if (firstSlash != -1) {
            String withoutParams = withoutKeyword.substring(0, firstSlash - 1);
            return withoutParams.trim();
        }
        return withoutKeyword.trim();
    }

    private static Map<String, String> parseParams(String description) {
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

    public static Task createTask(String description) {
        String keyword = parseKeyword(description);
        String name = parseName(description);
        Map<String, String> params = parseParams(description);
        switch (keyword) {
        case "deadline":
            return new DeadlineTask(name, params.get("by"));
        case "event":
            return new EventTask(name, params.get("at"));
        case "todo":
            return new TodoTask(name);
        default:
            return new Task(name);
        }
    }
}
