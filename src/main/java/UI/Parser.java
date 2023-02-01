package UI;
import java.util.Scanner;
import java.util.Arrays;

public class Parser {
    /*
     * Variables to store user input
     * scanner: Scanner object to take in user input
     * rawInput: user input as one String
     * inputList: user input in a list, with each element containing 1 word
     */
    private static Scanner scanner = new Scanner(System.in);
    private static String rawInput;
    private static String[] inputList;

    /*
     * Reads user input, updates rawInput and inputList
     */
    public static void readUserInput(){
        rawInput = scanner.nextLine();
        inputList = rawInput.split(" ");
    }

    /*
     * Obtains user command
     */
    public static String getCommand(){
        return inputList[0];
    }

    /*
     * Helper function to combine elements of inputList into a String
     */
    private static String getStringFromList(String[] inputList, int fromIndex, int toIndex) throws IllegalArgumentException{
        if (fromIndex >= toIndex){
            throw new IllegalArgumentException();
        }
        return String.join(" ", Arrays.copyOfRange(inputList, fromIndex, toIndex));
    }

    /*
     * Reads all words after command in user input
     */
    public static String getDescription(){
        return getStringFromList(inputList, 1, inputList.length);
    }
    
    /*
     * Reads all words after command and before 'at/by' in user input
     * Used for adding Event/Deadline tasks
     */
    public static String getDescription(int atbyPosition){
        return getStringFromList(inputList, 1, atbyPosition);
    }

    /*
     * Reads all words after 'at/by' in user input
     * Used for adding Event/Deadline tasks
     */
    public static String getDate(int atbyPosition){
        return getStringFromList(inputList, atbyPosition+1, inputList.length);
    }

    /*
     * Reads word after command as an integer
     * Used for mark/unmark/delete functions
     */
    public static int getTaskIndex(){
        return Integer.parseInt(inputList[1])-1;
    }

    /*
     * Finds the position of 'by' in user input
     */
    public static int getByPosition(){
        int byPosition = 0;
        for (int i=0; i<inputList.length; i++){
            if (inputList[i].equals("by")) {
                byPosition = i;
                break;
            }
        }
        return byPosition;
    }

    /*
     * Finds the position of 'at' in user input
     */
    public static int getAtPosition(){
        int atPosition = 0;
        for (int i=0; i<inputList.length; i++){
            if (inputList[i].equals("at")) {
                atPosition = i;
                break;
            }
        }
        return atPosition;
    }
}
