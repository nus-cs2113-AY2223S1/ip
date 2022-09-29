public interface FormatChecker {

    int TIME_IDENTIFIER_LENGTH = 5;
    int MODIFICATION_COMMAND_LENGTH = 2;
    String DEADLINE_IDENTIFIER = "/by: ";
    String EVENT_IDENTIFIER = "/at: ";

    /**
     * Check if the given string is empty
     *
     * @param string the string to check
     * @throws WrongCommandFormatException when the given string is empty
     */
    static void checkNullString(String string) throws WrongCommandFormatException {
        if (string.replaceAll("\\s+", "").equals("")) {
            throw new WrongCommandFormatException();
        }
    }

    /**
     * Find the location of time identifier in a given event or deadline command input
     *
     * @param identifier    the identifier of event or deadline
     * @param complexString the command input of event or deadline
     * @return the index of time identifier in the command input string
     * @throws WrongCommandFormatException when time identifier is missing in the command input
     */
    static int findIdentifierIndex(String identifier, String complexString) throws WrongCommandFormatException {
        int separatorIndex = complexString.indexOf(identifier);
        if (separatorIndex == -1) {
            throw new WrongCommandFormatException();
        }
        return separatorIndex;
    }

    /**
     * check if there are excess arguments in commands that modifies the task list, which are
     * delete, mark and unmark command
     *
     * @param cmd the full string of command input
     * @throws ExcessArgumentException when there are excess arguments in the command input
     */
    static void checkExcessArgument(String cmd) throws ExcessArgumentException {
        if (cmd.split(" ").length > MODIFICATION_COMMAND_LENGTH) {
            throw new ExcessArgumentException();
        }
    }

}
