package Duke;
import Duke.ui.UserInterface;

public class Duke {

    public static void main(String[] args) {
        String filePath = "Tasks.txt";
        UserInterface userInterface = new UserInterface(filePath);
        userInterface.giveGreeting();
        userInterface.runProgram();
        userInterface.giveFarewell();
    }
}
