package duke;

import java.io.FileNotFoundException;

public class Duke {

    private static boolean shouldExit = false;
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.setHomePath();
        dukeController.printWelcome();
        try {
            dukeController.readData();
        } catch (FileNotFoundException e){
            dukeController.printTaskList();
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
