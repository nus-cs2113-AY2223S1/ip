package duke.userinterface;

public class ConsoleInputParser {
    public static ConsoleCommand parseConsoleInput(ConsoleInput consoleInput) {
        String command = consoleInput.getCommand();

        switch (command) {
        case "bye":
            ConsoleCommandBye commandBye = new ConsoleCommandBye();
            return commandBye;
        case "list":
            ConsoleCommandList commandList = new ConsoleCommandList();
            return commandList;
        default:
            return null;
        }
    }
}
