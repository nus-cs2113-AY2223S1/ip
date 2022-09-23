package duke.parser;

import java.util.Map;
import java.util.TreeMap;

/**
 * The parser handles parsing of user input.
 */
public class Parser {
    /**
     * Extracts the command keyword from the user input.
     * 
     * @param description The user input string
     * @return The keyword
     */
    public static String parseKeyword(String description) {
        return description.split(" ")[0];
    }

    /**
     * Drops the command keyword from the user input string.
     * 
     * @param description The user input string
     * @return The user input string with the keyword removed
     */
    public static String removeKeyword(String description) {
        int firstSpace = description.indexOf(' ');
        if (firstSpace == -1) {
            return "";
        }
        String withoutKeyword = description.substring(firstSpace + 1);
        return withoutKeyword;
    }

    /**
     * Extracts the contents of the user input string that is not part of the
     * parameter list.
     * 
     * @param description The user input string
     * @return The command contents
     */
    public static String parseName(String description) {
        String withoutKeyword = removeKeyword(description);
        int firstSlash = withoutKeyword.indexOf('/');
        if (firstSlash != -1) {
            String withoutParams = withoutKeyword.substring(0, firstSlash - 1);
            return withoutParams.trim();
        }
        return withoutKeyword.trim();
    }

    /**
     * Extracts the parameters in the user input.
     * 
     * @param description The user input string
     * @return A map from the parameter key to parameter value
     */
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
