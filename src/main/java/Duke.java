public class Duke {
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.printWelcome();
        while (true){
            dukeController.getUserInput();
            dukeController.printNewLine();
            dukeController.handleUserInput();
        }
    }
}
