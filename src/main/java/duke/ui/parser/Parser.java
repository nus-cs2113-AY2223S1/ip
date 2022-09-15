package duke.ui.parser;

import duke.exception.InvalidCommandTypeException;

public class Parser {

    public Parser() {

    }

    public static Command parse(String rawInput) throws Exception {
        String[] tokens = rawInput.split("[ \t]+");
        String commandType = tokens[0];
        String rawArguments = tokens.length > 1 ? rawInput.substring(commandType.length()).trim() : "";

        Command command;

        switch (commandType) {
        case "bye":
            command = new CommandExit(rawArguments);
            break;
        case "list":
            command = new CommandList(rawArguments);
            break;
        case "mark":
            command = new CommandMark(rawArguments);
            break;
        case "unmark":
            command = new CommandUnmark(rawArguments);
            break;
        case "todo":
            command = new CommandToDo(rawArguments);
            break;
        case "deadline":
            command = new CommandDeadline(rawArguments);
            break;
        case "event":
            command = new CommandEvent(rawArguments);
            break;
        case "delete":
            command = new CommandDelete(rawArguments);
            break;
        default:
            throw new InvalidCommandTypeException();
        }
        command.verifyAndParse();
        return command;

    }

}
