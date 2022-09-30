package duke.utilityfunctions;

/**
 * Handles command-related behaviour for Duke, such as checking for valid commands, and parsing commands keyed in by user.
 */
public class Command {
    /**
     * Takes in input string and returns a boolean value whether it is a valid command or not
     * @param s first keyword of the string provided the user
     * @return whether is the provided keyword a valid command or not
     */
    public boolean isValidCommand(String s){
        String[] validCommandArray = {"bye", "list","find","unmark","mark","todo","deadline","event", "delete"};
        for(int i = 0; i < validCommandArray.length; i++) {
            if(s.equals(validCommandArray[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a provided string and returns the choice value as an integer
     * @param input the string provided by user
     * @return the choice value extracted from the input string
     */
    public int choiceParser(String input){
        String[] inputWords = input.split(" ");
        int choice = Integer.parseInt(inputWords[1]);
        return choice;
    }

}
