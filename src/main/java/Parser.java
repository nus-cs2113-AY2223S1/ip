
/**
 * parser class.
 */
public class Parser {

    /**
     * relevant commands are being called based on the user's command.
     */
    static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new bye(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new list(fullCommand);
        } else if(fullCommand.contains("mark")) {
            if (fullCommand.contains("unmark")) {
                return new unmark(fullCommand);
            } else {
                return new mark(fullCommand);
            }
        } else if (fullCommand.contains("todo")) {
            return new todo(fullCommand);
        } else if (fullCommand.contains("deadline")) {
            return new deadline(fullCommand);
        } else if (fullCommand.contains("event")) {
            return new event(fullCommand);
        } else if (fullCommand.contains("delete")) {
            return new delete(fullCommand);
        } else if (fullCommand.contains("find")) {
            return new find(fullCommand);
        } else {
            return new Command();
        }
    }


}
