package Duke.Commands;

import Duke.Ui;

/**
 * When executed, it prints a goodbye message and exits the program
 */
public class ByeCommand extends Command {
    public static final String BYE_COMMAND = "bye";
    private final String BYE_MESSAGE = "\tBob says goodbye :<";

    public ByeCommand() {
        System.out.println(BYE_MESSAGE);
        Ui.printGoodbye();
        System.exit(0);
    }
}
