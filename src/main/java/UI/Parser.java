package UI;
import java.util.Scanner;
import java.util.Arrays;

public class Parser {
    private static Scanner scanner = new Scanner(System.in);
    private static String rawInput;
    private static String[] inputList;

    public static void readUserInput(){
        rawInput = scanner.nextLine();
        inputList = rawInput.split(" ");
    }

    public static String getCommand(){
        return inputList[0];
    }

    private static String getStringFromList(String[] inputList, int fromIndex, int toIndex) throws IllegalArgumentException{
        if (fromIndex >= toIndex){
            throw new IllegalArgumentException();
        }
        return String.join(" ", Arrays.copyOfRange(inputList, fromIndex, toIndex));
    }

    public static String getDescription(){
        return getStringFromList(inputList, 1, inputList.length);
    }
    
    public static String getDescription(int atbyPosition){
        return getStringFromList(inputList, 1, atbyPosition);
    }

    public static String getDate(int atbyPosition){
        return getStringFromList(inputList, atbyPosition+1, inputList.length);
    }

    public static int getTaskIndex(){
        return Integer.parseInt(inputList[1]);
    }

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
