package Duke;

import Duke.commands.Command;

public class Parser {
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
