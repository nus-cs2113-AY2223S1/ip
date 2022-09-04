package duke.userinterface;

/**
 * Stores command and arguments entered by the user via standard input
 */
public class ConsoleInput {
    private String command;
    private String arguments;

    public ConsoleInput(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}
