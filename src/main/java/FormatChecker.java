public interface FormatChecker {

    int TIME_IDENTIFIER_LENGTH = 5;

    static void checkNullString(String string) throws WrongCommandFormatException {
        if (string.replaceAll("\\s+", "").equals("")) {
            throw new WrongCommandFormatException();
        }
    }

    static int findIdentifierIndex(String identifier, String complexString) throws WrongCommandFormatException {
        int separatorIndex = complexString.indexOf(identifier);
        if (separatorIndex == -1) {
            throw new WrongCommandFormatException();
        }
        return separatorIndex;
    }

    static void checkExcessArgument(String cmd) throws ExcessArgumentException {
        if (cmd.split(" ").length > 2) {
            throw new ExcessArgumentException();
        }
    }

}
