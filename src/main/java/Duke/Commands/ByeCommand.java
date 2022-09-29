package Duke.Commands;

import Duke.Ui;

public class ByeCommand extends Command {
    public static final String BYE_COMMAND = "bye";
    private final String BYE_MESSAGE = "\tBob says goodbye :<";

    public ByeCommand() {
        System.out.println(BYE_MESSAGE);
        Ui.printGoodbye();
        System.exit(0);
    }
}
