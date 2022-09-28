package Duke;

/**
 * Parser deals with making sense of the user input
 */
public class Parser {
    /**
     * Determine type of command from user input
     * @param command User input
     * @return Type of command to be executed
     */
    public static String commandType (String command) {
        String[] arr = command.split(" ", 2);
        return arr[0];
    }

    /**
     * Obtains description of task or command to be executed
     * @param command User input
     * @return Description of task or command
     */
    public static String commandActual (String command) {
        String[] arr = command.split(" ", 2);
        if (arr.length > 1) {
            return arr[1];
        }
        return null;
    }

    /**
     * Determine if bye command is issued by user
     * @param commandType User command
     * @return True if command is "bye"
     */
    public static Boolean isLast (String commandType) {
        return commandType == "bye" ? true : false;
    }

}
