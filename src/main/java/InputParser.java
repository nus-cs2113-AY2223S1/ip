import java.util.Scanner;

public class InputParser {
    protected final Scanner INPUT = new Scanner(System.in);
    public String readInput(){
        String input = INPUT.nextLine();
        return input;
    }

    public String[] parseInput(String userInput){
        String[] parsedInput = userInput.trim().split(" +", 2);
        return parsedInput;
    }

    public String[] parseTaskInformation(String userInput){
        String[] details = userInput.split("/", 2);
        for (int i = 0; i < details.length; i++){
            details[i] = details[i].trim();
            if (details[i].startsWith("by") || details[i].startsWith("at")){
                details[i] = details[i].substring(0,2) + ":" + details[i].substring(2);
            }
        }
        return details;
    }

    public int parseTaskIndex(String userInput){
        return Integer.parseInt(userInput);

    }
}
