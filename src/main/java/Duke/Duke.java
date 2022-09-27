package Duke;
import Duke.ui.UserInterface;

public class Duke {

    private static String filePath = "Tasks.txt";
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(filePath);
        userInterface.giveGreeting();
        userInterface.runProgram();
        userInterface.giveFarewell();
    }
}
