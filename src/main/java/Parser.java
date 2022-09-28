public class Parser {

    static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new bye(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new list(fullCommand);
        } else if(fullCommand.contains("mark")) {
            if (fullCommand.contains("unmark")) {
                //Unmark
                return new unmark(fullCommand);
            } else {
                //Mark
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
        } else {
            return new Command();
        }
    }


}
