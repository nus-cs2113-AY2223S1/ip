package Duke;

import Duke.commands.Command;

/**
 * Class to process raw input
 */
public class Parser {
    /**
     * This method splits the raw input given by user into the commands and input
     * and uses it to initialise a command object
     * @param input the raw string given by user
     * @return a command object that contains the command word and user input
     */
    public static Command parse (String input) {
        String[] splitInputs = input.split(" ", 2);
        String commandWord = splitInputs[0].toLowerCase();
        String userInput = " ";
        if (splitInputs.length == 2) {
            userInput = splitInputs[1];
        }
        return new Command(commandWord, userInput);
    }
}
