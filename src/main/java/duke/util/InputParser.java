package duke.util;

import duke.exception.UnknownCommandException;
import duke.exception.EmptyArgumentException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class InputParser implements Utilities{

    private static String userCommand;
    private static String inputBuffer;
    private static Scanner scanner;

    public InputParser() {
        inputBuffer = "";
        userCommand = "";
        scanner = new Scanner(System.in);
    }

    public static void init() {
        inputBuffer = "";
        userCommand = "";
        scanner = new Scanner(System.in);
    }

    public static void close() {
        inputBuffer = "";
        userCommand = "";
        scanner.close();
    }

    private static void clear() {
        inputBuffer = "";
        userCommand = "";
    }

    private static boolean isValidCommand() {
        switch (userCommand) {
            case ("list"):
            case ("mark"):
            case ("unmark"):
            case ("marked"):
            case ("todo"):
            case ("deadline"):
            case ("event"): //Fallthrough
            case ("delete"):
                return true;
            default:
                return false;
        }
    }

    private static boolean isCorrectInput(List<String> parsed) {
        if (userCommand.equals("list") || userCommand.equals("marked")){
            return true;
        }
        return (parsed.size() > 1);
    }

    private static ArrayList<String> parseParameter(String inputString, String optionFlag){
        int optionLen = 4;
        int optionIndex = inputString.indexOf(optionFlag);

        String descriptionMain = inputString.substring(0, optionIndex);
        String descriptionOption = inputString.substring(optionIndex + optionLen);

        return new ArrayList<>() {
            {
                add(descriptionMain);
                add(descriptionOption);
            }
        };
    }

    public static void parseUserInput(String userInput) throws UnknownCommandException, EmptyArgumentException {
        final int NUM_CMD_SPLIT = 2;
        //assume first word input by user is the command
        List<String> inputSplitBySpace = Arrays.asList( userInput.split(" ", NUM_CMD_SPLIT) );

        userCommand = inputSplitBySpace.get(0);

        if (!isValidCommand()) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!isCorrectInput(inputSplitBySpace) ) {
            throw new EmptyArgumentException("☹ OOPS!!! The description cannot be empty.");
        }

        //if the particular command requires arguments
        if ( inputSplitBySpace.size() > 1) {
            inputBuffer = inputSplitBySpace.get(1);
        }

    }

    public static void readInput() {
        userCommand = scanner.nextLine();
    }

    public static String getCommand() {
        return userCommand;
    }

    public static ArrayList<String> getTaskParameters() {
        ArrayList<String> parameters;

        switch (userCommand) {
            case ("todo"):
            case ("mark"):
            case ("delete"): //Fallthrough
            case ("unmark"):
                parameters = new ArrayList<>(){
                    {
                        add(inputBuffer);
                    }
                };
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
