public class Duke {

    private static boolean shouldExit = false;
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.printWelcome();
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
