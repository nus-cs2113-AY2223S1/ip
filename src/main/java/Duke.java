import java.util.Scanner;

public class Duke {
    public static void welcomeUser() {
        UI.welcomeUser();
        InputHandler.handleInput();
    }

    public static void main(String[] args) {
        welcomeUser();
        trackInput();
    }
}
