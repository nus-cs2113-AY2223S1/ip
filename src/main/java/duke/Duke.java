package duke;

import java.io.FileNotFoundException;

public class Duke {
    private static final String NO_SAVE_FILE = "You do not have any saved file!";
    private static boolean shouldExit = false;
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.setHomePath();
        dukeController.printWelcome();
        try {
            dukeController.readData();
        } catch (FileNotFoundException e){
            System.out.println(NO_SAVE_FILE);
            dukeController.printNewLine();
        }
        while (!shouldExit){
            dukeController.getUserInput();
            dukeController.printNewLine();
            dukeController.handleUserInput();
        }
    }

    public static void exitDuke(){
        shouldExit = true;
    }
}
