package duke;

public class Parser {

    public Command parseString(String input) {
        Command command;
        if (input.equals("bye")) {
            command = Command.BYE;
        } else if (!input.equals("")) {
            String[] words = input.split("\\s+");
            if (words.length == 0) {
                command = Command.INVALID;
            } else {
                switch (words[0]) {
                case "todo":
                    command = Command.TODO;
                    break;
                case "deadline":
                    command = Command.DEADLINE;
                    break;
                case "event":
                    command = Command.EVENT;
                    break;
                case "list":
                    command = Command.LIST;
                    break;
                case "mark":
                    command = Command.MARK;
                    break;
                case "unmark":
                    command = Command.UNMARK;
                    break;
                case "delete":
                    command = Command.DELETE;
                    break;
                default:
                    command = Command.INVALID;
                    break;
                }
            }
        } else {
            command = Command.INVALID;
        }
        return command;
    }

}