package duke.common;

/**
 * Provides a set of extra utilities such as a customised input split.
 */
public class Utils {
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;

    /**
     * Splits the user input into two parts, i.e. the command and the description.
     *
     * @param input A line of input entered by the user.
     * @return A string array of input tokens.
     */
    public static String[] splitInput(String input) {
        String[] inputTokens = input.split(DELIMITER, SPLIT_POSITION);
        if (!input.contains(DELIMITER)) {
            inputTokens = new String[]{input, EMPTY_STRING};
        }
        return inputTokens;
    }
}
