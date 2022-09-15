import handler.InputHandler;
import ui.UI;

public class Duke {
    public static void startProgram() {
        UI.welcomeUser();
        InputHandler.handleInput();
    }

    public static void main(String[] args) {
        startProgram();
    }
}
