package duke.command;

import java.util.Arrays;

public class StringTools {
    // this class focuses on getting information from a string or string[] and returning it

    public static String[] splitString(String s) {
        return s.split(" ");
    }

    public static String returnKeyword(String[] input) {
        return input[0].toLowerCase();
    }

    public static String concatenate(String[] s) {
        String combinedString = "";
        for (int i = 0; i < s.length; i++) {
            combinedString += s[i];
            if (i != s.length - 1) {
                combinedString += " ";
            }
        }
        return combinedString;
    }

    public static String[] copyArray(String[] s, int start, int end) {
        return Arrays.copyOfRange(s, start, end);
    }

    public static int searchArray(String[] s, String indicator) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(indicator)) {
                return i;
            }
        }
        return -1; //not found
    }

    public static int indicatorLocator(String[] s) {
        String keyword = returnKeyword(s);
        switch (keyword) {
        case "deadline":
            return searchArray(s, "/by");
        case "event":
            return searchArray(s, "/at");
        default:
            return 2;
        }
    }

    public static String returnDescription(String[] s, String keyword) {
        switch (keyword) {
        case "mark":
        case "unmark":
            return s[1];
        case "todo":
            String[] truncatedInput = StringTools.copyArray(s, 1, s.length);
            return StringTools.concatenate(truncatedInput);
        case "event":
        case "deadline":
            int indicatorPosition = StringTools.indicatorLocator(s);
            String[] splitDescription = StringTools.copyArray(s, 1, indicatorPosition);
            return StringTools.concatenate(splitDescription);
        default:
            return "";
        }
    }

    public static String returnTime(String[] s, String keyword) {
        switch (keyword) {
        case "event":
        case "deadline":
            int indicatorPosition = StringTools.indicatorLocator(s);
            String[] splitTime = StringTools.copyArray(s, indicatorPosition + 1, s.length);
            return StringTools.concatenate(splitTime);
        default:
            return "";
        }
    }
}
