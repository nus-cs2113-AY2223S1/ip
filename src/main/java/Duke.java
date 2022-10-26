import ui.Console;
import static ui.Messages.helloMessage;

/**
 * Runs the Duke application.
 */
public class Duke {
    public static void main(String[] args) {
        helloMessage();
        new Console().runDuke();
    }
}