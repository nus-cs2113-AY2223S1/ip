package duke;

/**
 * Duke is a task list manager.
 */
public class Duke {

    /** Boolean to indicate if "bye" has been input by user */
    private static boolean shouldExit = false;

    /**
     * Calls dukeController to handle program flow of Duke.
     *
     * @param args Null
     */
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.initialise();
        while (!shouldExit){
            dukeController.getUserInput();
            dukeController.handleUserInput();
        }
    }

    /**
     * For dukeController to call when "bye" is input by user
     */
    public static void exitDuke(){
        shouldExit = true;
    }
}
