package duke;

import java.util.Scanner;

public class Parser {
    private final String EMPTY_DESCRIPTION_ERROR = ":( The description of a %s cannot be empty!";
    private final String EMPTY_DATE_ERROR = ":( A %s task must have a date!";
    private final String UNKNOWN_USER_COMMAND_ERROR = ":( Sorry I do not know what that means!";
    private final String EMPTY_INPUT_ERROR = ":( Input cannot be empty";
    private final String INVALID_TASK_INDEX_ERROR = ":( You must specify a valid index for a %s action!";
    private final String INVALID_SEARCH_KEYWORD_ERROR = ":( you must give one keyword for searching!";
    protected final Scanner INPUT = new Scanner(System.in);
    public String readInput(){
        String input = INPUT.nextLine();
        return input;

    }
    public String[] parseInput(String userInput) throws IllegalInputException{
        String[] parsedInput = userInput.trim().split(" +", 2);
        String userCommand = parsedInput[0];
        switch (userCommand){
        case "find":
            if (parsedInput.length == 1){
                throw new IllegalInputException(INVALID_SEARCH_KEYWORD_ERROR);
            }
        case "mark":
        case "unmark":
        case "delete":
            if (parsedInput.length == 1){
                throw new IllegalInputException(String.format(INVALID_TASK_INDEX_ERROR, userCommand));
            }
        case "todo":
        case "deadline":
        case "event":
            if (parsedInput.length == 1) {
                throw new IllegalInputException(String.format(EMPTY_DESCRIPTION_ERROR, userCommand));
            }
        case "list":
        case "bye":
            break;
        case "":
            throw new IllegalInputException(EMPTY_INPUT_ERROR);
        default:
            throw new IllegalInputException(UNKNOWN_USER_COMMAND_ERROR);
        }
        return parsedInput;
    }
    public String[] parseTaskInformation(String userInput, String userCommand) throws IllegalInputException {
        String[] details = userInput.split("/", 2);
        if (userCommand.equals("event") || userCommand.equals("deadline")){
            if (details[0].trim().equals("")) {
                throw new IllegalInputException(String.format(EMPTY_DESCRIPTION_ERROR, userCommand));
            } else if (details.length < 2){
                throw new IllegalInputException(String.format(EMPTY_DATE_ERROR, userCommand));
            }
        }
        for (int i = 0; i < details.length; i++){
            details[i] = details[i].trim();
            if (details[i].startsWith("by") || details[i].startsWith("at")){
                details[i] = details[i].substring(0,2) + ":" + details[i].substring(2);
            }
        }
        return details;
    }
    public int parseTaskIndex(String userInput, String userCommand) throws IllegalInputException{
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e){
            throw new IllegalInputException(String.format(INVALID_TASK_INDEX_ERROR, userCommand));
        }

    }
}
