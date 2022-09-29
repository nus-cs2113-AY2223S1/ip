package duke.util;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.exception.EmptyArgumentException;

import duke.util.asset.Deadline;
import duke.util.asset.Event;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import duke.command.Command;

public class InputParser implements Utilities{

    private static String userCommand;
    private static String inputBuffer;
    private static ArrayList<String> parameters;

    public InputParser() {
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

    private static ArrayList<String> parseParameter(String inputString, String optionFlag){
        int optionLen = optionFlag.length() + 1;
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
        List<String> inputSplitBySpace = Arrays.asList(userInput.split(" ", NUM_CMD_SPLIT));

        userCommand = inputSplitBySpace.get(0);

        if (!CommandProcessor.isValidCommand(userCommand)) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!CommandProcessor.isCorrectArgumentLength(userCommand,inputSplitBySpace)) {
            throw new EmptyArgumentException("☹ OOPS!!! The description cannot be empty.");
        }

        //if the particular command has arguments
        if ( inputSplitBySpace.size() > 1) {
            inputBuffer = inputSplitBySpace.get(1).trim();
        }

    }

    public static String getCommand() {
        return userCommand;
    }

    public static void getTaskParameters() {

        switch (userCommand) {
            case (Deadline.COMMAND):
                parameters = parseParameter(inputBuffer, "/" + Deadline.OPTIONFLAG);
                break;
            case (Event.COMMAND):
                parameters = parseParameter(inputBuffer, "/" + Event.OPTIONFLAG);
                break;
            default:
                parameters = new ArrayList<>(){
                    {
                        add(inputBuffer);
                    }
                };
        }

    }

    public static Command parse(String command) throws DukeException {

        try {
            //get the command keyword
            parseUserInput(command);
            getTaskParameters();
        } catch (DukeException e) {
            throw new DukeException(e.getErrorMessage());
        }

        return CommandProcessor.createCommand(userCommand, parameters);
    }

}
