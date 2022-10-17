package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.exception.EmptyArgumentException;

import duke.util.asset.Deadline;
import duke.util.asset.Event;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import duke.command.Command;

/**
 * A class to parse user input
 * The Parser takes in the full user input and process it to extract the command, description, and argument (if any)
 * The parsed results are stored in private variables within the Parser class
 * Throws Exceptions when the input is invalid
 * Assisted by Command Processor to check for validity of command and construct Command class
 */
public class Parser implements Utilities {

    private static String userCommand;
    private static String inputBuffer;
    private static ArrayList<String> parameters;

    public Parser() {
        inputBuffer = "";
        userCommand = "";
        parameters = new ArrayList<>();
    }

    public static void init() {
        inputBuffer = "";
        userCommand = "";
        parameters = new ArrayList<>();
    }

    public static void close() {
        inputBuffer = "";
        userCommand = "";
        parameters.clear();
    }

    /**
     * Parse for the parameters
     *
     * @param inputString the user input without the command keyword
     * @param optionFlag what option flag to parse for
     * @return a list containing the task description and the timing (if any)
     * @throws InvalidArgumentException if the option is entered wrongly by the user
     */
    private static ArrayList<String> parseParameter(String inputString, String optionFlag) throws InvalidArgumentException {
        int optionLen = optionFlag.length() + 1;
        int optionIndex = inputString.indexOf(optionFlag);

        if (optionIndex == -1) {
            throw new InvalidArgumentException("Error: Wrong option flag, try again");
        }

        int indexRightAfterOption = optionIndex + optionLen - 1;

        //if user misentered the option such as "/bys" instead of "/by"
        if (inputString.charAt(indexRightAfterOption) != ' '){
            throw new InvalidArgumentException("Error: Wrong option format, please refer to help guide");
        }

        String descriptionMain = inputString.substring(0, optionIndex).trim();
        String descriptionOption = inputString.substring(optionIndex + optionLen).trim();

        return new ArrayList<>() {
            {
                add(descriptionMain);
                add(descriptionOption);
            }
        };
    }

    /**
     * Parse for command keyword and check for its validity
     * Populate the parser buffer if the command has descriptions, taskIndex or options
     *
     * @param userInput the full user command
     * @throws UnknownCommandException if user entered an unknown command
     * @throws EmptyArgumentException if user left some fields of the command empty
     */
    public static void parseUserInput(String userInput) throws UnknownCommandException, EmptyArgumentException {
        final int NUM_CMD_SPLIT = 2;
        //assume first word input by user is the command
        List<String> inputSplitBySpace = Arrays.asList(userInput.split(" ", NUM_CMD_SPLIT));

        userCommand = inputSplitBySpace.get(0);

        if (!CommandProcessor.isValidCommand(userCommand)) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!CommandProcessor.isCorrectArgumentLength(userCommand,inputSplitBySpace)) {
            throw new EmptyArgumentException("☹ OOPS!!! The description cannot be empty.");
        }

        //if the commands require options (/by or /at)
        if ( inputSplitBySpace.size() > 1) {
            inputBuffer = inputSplitBySpace.get(1).trim();
        }

    }

    public static String getCommand() {
        return userCommand;
    }

    /**
     * Get the parameters (description, task index, or options) as per specified by the command type
     *
     * @throws DukeException if encounter errors in parsing for parameter
     */
    public static void getTaskParameters() throws DukeException {
        try {
            switch (userCommand) {
            case (Deadline.COMMAND):
                parameters = parseParameter(inputBuffer, "/" + Deadline.OPTIONFLAG);
                break;
            case (Event.COMMAND):
                parameters = parseParameter(inputBuffer, "/" + Event.OPTIONFLAG);
                break;
            default:
                parameters = new ArrayList<>() {
                    {
                        add(inputBuffer);
                    }
                };
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns the command to be executed
     * The raw user command will be parsed and stored within private variables of the parser.
     *
     * @param command the full user command
     * @return command class as created by the CommandProcessor
     * @throws DukeException if encounter error in parsing user input
     */
    public static Command parse(String command) throws DukeException {

        try {
            //get the command keyword
            parseUserInput(command);
            getTaskParameters();
        } catch (DukeException e) {
            throw e;
        }

        return CommandProcessor.createCommand(userCommand, parameters);
    }

}
