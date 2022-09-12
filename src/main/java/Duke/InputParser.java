package Duke;

public class InputParser {

    private String userCommand;
    private String inputBuffer;

    public InputParser() {
        inputBuffer = "";
        userCommand = "";
    }

    private void clear() {
        inputBuffer = "";
        userCommand = "";
    }

    private boolean isValidCommand() {
        switch (userCommand) {
            case ("list"): //Fallthrough
            case ("mark"): //Fallthrough
            case ("unmark"): //Fallthrough
            case ("todo"): //Fallthrough
            case ("deadline"): //Fallthrough
            case ("event"):
                return true;
            default:
                return false;
        }
    }
    private boolean isCorrectInput(String[] parsed ) {
        if (userCommand.equals("list")){
            return true;
        }
        return (parsed.length > 1);
    }

    private String[] parseParameter(String inputString, String optionFlag){
        int OPTION_LEN = 4;
        int optionIndex = inputString.indexOf(optionFlag);

        String descriptionMain = inputString.substring(0, optionIndex);
        String descriptionOption = inputString.substring(optionIndex + OPTION_LEN);

        return new String[]{ descriptionMain , descriptionOption };
    }

    public void parseUserInput(String userInput) throws UnknownCommandException, DukeException {

        String[] inputSplitBySpace = userInput.split(" ", 2);
        //assume first word input by user is the command

        userCommand = inputSplitBySpace[0];

        if (!isValidCommand()) {
            throw new UnknownCommandException();
        }

        if (!isCorrectInput(inputSplitBySpace) ) {
            throw new DukeException();
        }

        if ( inputSplitBySpace.length > 1) {
            inputBuffer = inputSplitBySpace[1];
        }

    }

    public String getCommand() {
        return userCommand;
    }

    public String[] getTaskParameters() {
        String[] parameters;

        switch (userCommand) {
            case ("todo"): //Fallthrough
            case ("mark"): //Fallthrough
            case ("unmark"):
                parameters = new String[]{ inputBuffer };
                break;
            case ("deadline"):
                parameters = parseParameter(inputBuffer, "/by");
                break;
            case ("event"):
                parameters = parseParameter(inputBuffer, "/at");
                break;
            default:
                parameters = null;
        }

        clear();
        return parameters;
    }

}
