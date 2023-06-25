package process;

import command.Command;

/**
 * Parses the user input text
 */
public class Parser {
    /**
     * @param inputText the user input
     * @return an instance of Command class with the command type and command args
     */
    public static Command parse (String inputText) {
        final String[] splits = inputText.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = splits.length == 2 ? splits : new String[] { splits[0], "" };
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        return new Command(commandType,commandArgs);
    }
}
