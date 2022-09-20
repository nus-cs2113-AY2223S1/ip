package Duke;
import Duke.ui.UserInterface;

public class Duke {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.giveGreeting();
        userInterface.runProgram();
        userInterface.giveFarewell();
    }
}
