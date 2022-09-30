package duke.Storage;

import duke.exception.StorageReadException;

/**
 * This class is for parsing parts of stored data for loading into this program.
 */
public class FileParser {
    private static final String SPACES_BETWEEN_WORDS = " ";

    /**
     * Returns the due time for deadline tasks and the starting time for event tasks.
     *
     * @param unprocessedInput the truncated stored data with only description and time fields left
     * @param separatorPosition the | separator before the time fields of deadline and event tasks
     * @return the time of the tasks
     */
    public static String getStoredTime(String unprocessedInput, int separatorPosition) {
        return unprocessedInput.substring(separatorPosition + 2);
    }

    /**
     * Returns the description for deadline and event tasks.
     *
     * @param unprocessedInput the truncated stored data with only description and time fields left
     * @param separatorPosition the | separator before the time fields of deadline and event tasks
     * @return the description of the tasks
     */
    public static String getStoredDescription(String unprocessedInput, int separatorPosition) {
        return unprocessedInput.substring(0, separatorPosition - 1);
    }

    /**
     * Converts the boolean value representing completion status of task
     * that is stored as an integer to boolean and returns it.
     *
     * @param input the stored tasks
     * @return the completion status of the stored task
     * @throws StorageReadException If anomalous characters is loaded instead of just 0 or 1.
     */
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
