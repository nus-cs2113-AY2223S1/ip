public class Duke {
    /**
     * Initializes application and starts interaction with user.
     */
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();

        ConsoleInterface.printLogo();

        ConsoleInterface.printLineSeparator();
        ConsoleInterface.printGreetingMessage();
        ConsoleInterface.printLineSeparator();

        consoleInterface.executeProgram();

        ConsoleInterface.printLineSeparator();
        ConsoleInterface.printGoodbyeMessage();
        ConsoleInterface.printLineSeparator();
    }
}
