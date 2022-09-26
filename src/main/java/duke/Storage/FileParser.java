package duke.Storage;

import duke.exception.StorageReadException;

public class FileParser {
    private static String SPACES_BETWEEN_WORDS = " ";

    public static String getStoredTime(String unprocessedInput, int separatorPosition) {
        String time = unprocessedInput.substring(separatorPosition + 2);
        return time;
    }

    public static String getStoredDescription(String unprocessedInput, int separatorPosition) {
        String description = unprocessedInput.substring(0, separatorPosition - 1);
        return description;
    }

    static boolean getStoredBoolean(String input) throws StorageReadException {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        int booleanNumber = Integer.parseInt(splitInput[2]);
        if (booleanNumber == 1) {
            return true;
        } else if (booleanNumber == 0) {
            return false;
        } else {
            throw new StorageReadException("boolean");
        }
    }
}
