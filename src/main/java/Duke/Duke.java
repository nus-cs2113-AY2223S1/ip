package Duke;
import Duke.ui.UserInterface;

/**
 * The main class of the program
 */
public class Duke {

    /**
     * This method starts running the program
     */
    public static void main(String[] args) {
        String filePath = "Tasks.txt";
        UserInterface userInterface = new UserInterface(filePath);
        userInterface.giveGreeting();
        userInterface.runProgram();
        userInterface.giveFarewell();
    }
}
