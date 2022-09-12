package duke.command;

import duke.exception.*;

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

    public static int searchArray(String[] s, String target) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(target)) {
                return i;
            }
        }
        return -1; //not found
    }

    public static int indicatorLocator(String[] s, String keyword) {
        switch (keyword) {
        case "deadline":
            return searchArray(s, "/by");
        case "event":
            return searchArray(s, "/at");
        default:
            return 0;
        }
    }

    public static String returnDescription(String[] s, String keyword) throws
            MissingIndicatorException, MissingIntegerException {
        switch (keyword) {
        case "mark":
        case "unmark":
        case "delete":
            try {
                return s[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingIntegerException();
            }
        case "todo":
            String[] truncatedInput = copyArray(s, 1, s.length);
            return concatenate(truncatedInput);
        case "event":
        case "deadline":
            try {
                int indicatorPosition = indicatorLocator(s, keyword);
                String[] splitDescription = copyArray(s, 1, indicatorPosition);
                return concatenate(splitDescription);
            } catch (IllegalArgumentException e) {
                throw new MissingIndicatorException();
            }
        default:
            return "";
        }
    }

    public static String returnTime(String[] s, String keyword) throws MissingIndicatorException {
        switch (keyword) {
        case "event":
        case "deadline":
            try {
                int indicatorPosition = indicatorLocator(s, keyword);
                String[] splitTime = copyArray(s, indicatorPosition + 1, s.length);
                return concatenate(splitTime);
            } catch (IllegalArgumentException e) {
                throw new MissingIndicatorException();
            }
        default:
            return "";
        }
    }
}
