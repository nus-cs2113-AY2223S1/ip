package consoleCommands;

/**
 * Class to parse commands inputted into the programme
 */
public class Parser {
    /**
     * Method takes in an input string, and converts it into a command class object (with command and arguments)
     * Method splits input into 2 strings, the first one being the command, and the second one is the arguments
     * If input only has command, argument is passed as empty string
     * @param input is the input string from command line
     * @return command class object to Duke.java for execution
     */
    public Command Parse(String input) {
        String[] tempInput = input.split(" ", 2);
        if (tempInput.length == 2) {
            String command = tempInput[0];
            String arguments = tempInput[1];
            return new Command(command, arguments);
        }
        return new Command(input, "");
    }
}