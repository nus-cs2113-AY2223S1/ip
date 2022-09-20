package duke.common;

public class Utils {
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;
    public static String[] splitInput(String input) {
        String[] inputTokens = input.split(DELIMITER, SPLIT_POSITION);
        if (!input.contains(DELIMITER)) {
            inputTokens = new String[]{input, EMPTY_STRING};
        }
        return inputTokens;
    }
}
