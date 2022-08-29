import java.util.Scanner;
import java.util.ArrayList;

public class Juke {

    // MAIN FUNCTION
    public static void main(String[] args) {
        
        // INITIAL PRINTING
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
        
        // VARS
        String userInput = "";
        Scanner scanObj = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();
        
        // JUKE LOGIC
        while(!userInput.equals("bye")){
            userInput = scanObj.nextLine();  // Read user input

            // SWITCH-CASE
            switch(userInput){
            case "list":
                System.out.println(line);
                for(int i = 0; i < tasks.size(); i++){
                    int j = i + 1;
                    System.out.println(j + ". " + tasks.get(i) + "\n");
                }
                System.out.println(line);
                break;
            case "bye":
                break;
            default:
                // keep adding to list of tasks
                tasks.add(userInput);
                System.out.println("Input recorded: " + userInput + "\n");
                break;
            }
        }

        scanObj.close();
        System.out.println("\nSee you again!");
    }
}
