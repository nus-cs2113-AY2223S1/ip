package duke;

import java.io.FileNotFoundException;

public class Duke {
    private static boolean shouldExit = false;
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.initialise();
        while (!shouldExit){
            dukeController.getUserInput();
            dukeController.handleUserInput();
        }
    }

    public static void exitDuke(){
        shouldExit = true;
    }
}
