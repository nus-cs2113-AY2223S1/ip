package duke;

import duke.userinterface.ConsoleInterface;

/**
 * Entrypoint of the Duke application.
 */
public class Duke {
    /**
     * Initializes application and starts interaction with user.
     */
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface();

        ConsoleInterface.printLogo();

        ConsoleInterface.printLineSeparator();

        ConsoleInterface.printGreetingMessage();

        consoleInterface.executeProgram();

        ConsoleInterface.printGoodbyeMessage();

        ConsoleInterface.printLineSeparator();
    }
}
