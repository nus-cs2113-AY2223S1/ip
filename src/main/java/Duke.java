import java.util.Scanner;  // Import the Scanner class
public class Duke {

    // MAIN FUNCTION
    public static void main(String[] args) {
        
        // INITIAL PRINTING
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Zuck!\n What can I do for you?\n" + line);
        
        // VARS
        String userInput = "";
        Scanner scanObj = new Scanner(System.in); // creates new scanner object
        
        while(!userInput.equals("bye")){
            userInput = scanObj.nextLine();  // Read user input
            System.out.println("You said:" + userInput);
        }

        scanObj.close();
        System.out.println("See you again!");
    }
}
